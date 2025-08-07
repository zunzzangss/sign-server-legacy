package com.bezzangss.sign.application.documents.document.application;

import com.bezzangss.sign.application.documents.associate.cc.port.in.CcCommandApplicationPort;
import com.bezzangss.sign.application.documents.associate.publisher.port.in.PublisherCommandApplicationPort;
import com.bezzangss.sign.application.documents.associate.signer.port.in.SignerApplicationCommandPort;
import com.bezzangss.sign.application.documents.document.application.mapper.DocumentApplicationMapper;
import com.bezzangss.sign.application.documents.document.port.in.DocumentCommandApplicationPort;
import com.bezzangss.sign.application.documents.document.port.in.dto.request.DocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents.document.port.in.dto.request.common.CcInDocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents.document.port.in.dto.request.common.PublisherInDocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents.document.port.in.dto.request.common.SignerInDocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents.document.port.out.DocumentRepositoryPort;
import com.bezzangss.sign.common.enums.EnumConverter;
import com.bezzangss.sign.domain.documents.document.aggregate.Document;
import com.bezzangss.sign.domain.documents.document.dto.DocumentDomainCreateRequest;
import com.bezzangss.sign.domain.documents.metadocument.MetaDocumentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Component
public class DocumentCommandApplication implements DocumentCommandApplicationPort {
    private final DocumentApplicationMapper documentApplicationMapper;
    private final DocumentRepositoryPort documentRepositoryPort;

    private final PublisherCommandApplicationPort publisherCommandApplicationPort;
    private final SignerApplicationCommandPort signerApplicationCommandPort;
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
}
