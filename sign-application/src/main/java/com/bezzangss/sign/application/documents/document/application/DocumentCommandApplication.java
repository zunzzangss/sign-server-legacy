package com.bezzangss.sign.application.documents.document.application;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.documents.associate.cc.port.in.CcCommandApplicationPort;
import com.bezzangss.sign.application.documents.associate.publisher.port.in.PublisherCommandApplicationPort;
import com.bezzangss.sign.application.documents.associate.signer.application.bridge.SignerQueryApplicationBridge;
import com.bezzangss.sign.application.documents.associate.signer.port.in.SignerApplicationCommandPort;
import com.bezzangss.sign.application.documents.associate.signer.port.in.SignerApplicationQueryPort;
import com.bezzangss.sign.application.documents.document.application.mapper.DocumentApplicationMapper;
import com.bezzangss.sign.application.documents.document.port.in.DocumentCommandApplicationPort;
import com.bezzangss.sign.application.documents.document.port.in.dto.request.DocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents.document.port.in.dto.request.common.CcInDocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents.document.port.in.dto.request.common.PublisherInDocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents.document.port.in.dto.request.common.SignerInDocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents.document.port.out.DocumentRepositoryPort;
import com.bezzangss.sign.common.enums.EnumConverter;
import com.bezzangss.sign.domain.documents.associate.signer.aggregate.Signer;
import com.bezzangss.sign.domain.documents.document.aggregate.Document;
import com.bezzangss.sign.domain.documents.document.dto.DocumentDomainCreateRequest;
import com.bezzangss.sign.domain.documents.document.event.DocumentDomainEvent;
import com.bezzangss.sign.domain.documents.document.service.DocumentDomainService;
import com.bezzangss.sign.domain.documents.metadocument.MetaDocumentType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static com.bezzangss.sign.common.exception.ErrorCode.DOCUMENT_NOT_FOUND_EXCEPTION;
import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@RequiredArgsConstructor
@Transactional
@Component
public class DocumentCommandApplication implements DocumentCommandApplicationPort {
    private final DocumentApplicationMapper documentApplicationMapper;
    private final DocumentDomainService documentDomainService;
    private final DocumentRepositoryPort documentRepositoryPort;

    private final PublisherCommandApplicationPort publisherCommandApplicationPort;
    private final SignerApplicationCommandPort signerApplicationCommandPort;
    private final SignerApplicationQueryPort signerApplicationQueryPort;
    private final SignerQueryApplicationBridge signerQueryApplicationBridge;
    private final CcCommandApplicationPort ccCommandApplicationPort;

    @Override
    public String create(DocumentApplicationCreateRequest documentApplicationCreateRequest) {
        // TODO: validation

        Document document = Document.create(
                DocumentDomainCreateRequest.builder()
                        .name(documentApplicationCreateRequest.getName())
                        .description(documentApplicationCreateRequest.getDescription())
                        .metaDocumentType(EnumConverter.from(MetaDocumentType.class, documentApplicationCreateRequest.getMetaDocumentType()))
                        .metaDocumentId(documentApplicationCreateRequest.getMetaDocumentId())
                        .build()
        );
        String id = documentRepositoryPort.create(documentApplicationMapper.toRepositoryCreateRequest(document));

        this.createPublisher(documentApplicationCreateRequest.getPublisher(), id);
        this.createSigners(documentApplicationCreateRequest.getSigners(), id);
        this.createCcs(documentApplicationCreateRequest.getCcs(), id);

        return id;
    }

    @Override
    public void process(String id) {
        if (ObjectUtils.isEmpty(id)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");

        Document document = this.findDocumentById(id);
        List<Signer> signers = signerQueryApplicationBridge.findAllDomainByDocumentId(id);

        documentDomainService.process(signers, document);
        this.update(document);
    }

    @EventListener
    public void eventListener(DocumentDomainEvent documentDomainEvent) {

    }

    private void createPublisher(PublisherInDocumentApplicationCreateRequest publisher, String id) {
        publisherCommandApplicationPort.create(documentApplicationMapper.toApplicationCreateRequest(publisher, id));
    }

    private void createSigners(List<SignerInDocumentApplicationCreateRequest> signers, String id) {
        for (SignerInDocumentApplicationCreateRequest signer : signers) {
            signerApplicationCommandPort.create(documentApplicationMapper.toApplicationCreateRequest(signer, id));
        }
    }

    private void createCcs(List<CcInDocumentApplicationCreateRequest> ccs, String id) {
        if (ObjectUtils.isEmpty(ccs)) {
            for (CcInDocumentApplicationCreateRequest cc : ccs) {
                ccCommandApplicationPort.create(documentApplicationMapper.toApplicationCreateRequest(cc, id));
            }
        }
    }

    private Document findDocumentById(String id) {
        return documentRepositoryPort.findById(id)
                .map(documentApplicationMapper::toDomain)
                .orElseThrow(() -> new ApplicationException(DOCUMENT_NOT_FOUND_EXCEPTION, id));
    }

    private String update(Document document) {
        return documentRepositoryPort.update(documentApplicationMapper.toRepositoryUpdateRequest(document));
    }
}
