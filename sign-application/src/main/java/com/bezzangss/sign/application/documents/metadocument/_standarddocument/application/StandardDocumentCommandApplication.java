package com.bezzangss.sign.application.documents.metadocument._standarddocument.application;

import com.bezzangss.sign.application.documents.metadocument._standarddocument.application.mapper.StandardDocumentApplicationMapper;
import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.in.StandardDocumentCommandApplicationPort;
import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.in.dto.request.StandardDocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.out.StandardDocumentRepositoryPort;
import com.bezzangss.sign.application.documents.metadocument.port.in.MetaDocumentCommandApplicationPort;
import com.bezzangss.sign.application.documents.metadocument.port.in.dto.request.MetaDocumentApplicationCreateDocumentRequest;
import com.bezzangss.sign.application.documents.metadocument.port.in.dto.request.MetaDocumentApplicationReplicateResourceByBaseDocumentRequest;
import com.bezzangss.sign.domain.documents.metadocument.MetaDocumentType;
import com.bezzangss.sign.domain.documents.metadocument.standarddocument.aggregate.StandardDocument;
import com.bezzangss.sign.domain.documents.metadocument.standarddocument.dto.StandardDocumentDomainCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class StandardDocumentCommandApplication implements StandardDocumentCommandApplicationPort {
    private final StandardDocumentApplicationMapper standardDocumentApplicationMapper;
    private final StandardDocumentRepositoryPort standardDocumentRepositoryPort;

    private final MetaDocumentCommandApplicationPort metaDocumentCommandApplicationPort;

    @Override
    public String create(StandardDocumentApplicationCreateRequest standardDocumentApplicationCreateRequest) {
        StandardDocument standardDocument = StandardDocument.create(
                StandardDocumentDomainCreateRequest.builder()
                        .name(standardDocumentApplicationCreateRequest.getName())
                        .description(standardDocumentApplicationCreateRequest.getDescription())
                        .build()
        );
        String id = standardDocumentRepositoryPort.create(standardDocumentApplicationMapper.toRepositoryCreateRequest(standardDocument));

        metaDocumentCommandApplicationPort.createDocument(
                MetaDocumentApplicationCreateDocumentRequest.builder()
                        .metaDocumentId(id)
                        .metaDocumentType(MetaDocumentType.STANDARD_DOCUMENT.name())
                        .document(standardDocumentApplicationCreateRequest.getDocument())
                        .build()
        );

        metaDocumentCommandApplicationPort.replicateResourceByBaseDocument(
                MetaDocumentApplicationReplicateResourceByBaseDocumentRequest.builder()
                        .metaDocumentId(id)
                        .metaDocumentType(MetaDocumentType.STANDARD_DOCUMENT.name())
                        .baseDocument(standardDocumentApplicationCreateRequest.getBaseDocument())
                        .build()
        );

        return id;
    }
}
