package com.bezzangss.sign.application.documents.metadocument._groupdocument.application;

import com.bezzangss.sign.application.documents.metadocument._groupdocument.application.mapper.GroupDocumentApplicationMapper;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.in.GroupDocumentCommandApplicationPort;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.in.dto.request.GroupDocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.out.GroupDocumentRepositoryPort;
import com.bezzangss.sign.application.documents.metadocument.port.in.MetaDocumentCommandApplicationPort;
import com.bezzangss.sign.application.documents.metadocument.port.in.dto.request.DocumentInMetaDocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents.metadocument.port.in.dto.request.MetaDocumentApplicationCreateDocumentRequest;
import com.bezzangss.sign.application.documents.metadocument.port.in.dto.request.MetaDocumentApplicationReplicateResourceByBaseDocumentRequest;
import com.bezzangss.sign.domain.documents.metadocument.MetaDocumentType;
import com.bezzangss.sign.domain.documents.metadocument.groupdocument.aggregate.GroupDocument;
import com.bezzangss.sign.domain.documents.metadocument.groupdocument.dto.GroupDocumentDomainCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class GroupDocumentCommandApplication implements GroupDocumentCommandApplicationPort {
    private final GroupDocumentApplicationMapper groupDocumentApplicationMapper;
    private final GroupDocumentRepositoryPort groupDocumentRepositoryPort;

    private final MetaDocumentCommandApplicationPort metaDocumentCommandApplicationPort;

    @Override
    public String create(GroupDocumentApplicationCreateRequest groupDocumentApplicationCreateRequest) {
        GroupDocument groupDocument = GroupDocument.create(
                GroupDocumentDomainCreateRequest.builder()
                        .name(groupDocumentApplicationCreateRequest.getName())
                        .description(groupDocumentApplicationCreateRequest.getDescription())
                        .build()
        );
        String id = groupDocumentRepositoryPort.create(groupDocumentApplicationMapper.toRepositoryCreateRequest(groupDocument));

        for (DocumentInMetaDocumentApplicationCreateRequest document : groupDocumentApplicationCreateRequest.getDocuments()) {
            metaDocumentCommandApplicationPort.createDocument(
                    MetaDocumentApplicationCreateDocumentRequest.builder()
                            .metaDocumentId(id)
                            .metaDocumentType(MetaDocumentType.GROUP_DOCUMENT.name())
                            .document(document)
                            .build()
            );
        }

        metaDocumentCommandApplicationPort.replicateResourceByBaseDocument(
                MetaDocumentApplicationReplicateResourceByBaseDocumentRequest.builder()
                        .metaDocumentId(id)
                        .metaDocumentType(MetaDocumentType.GROUP_DOCUMENT.name())
                        .baseDocument(groupDocumentApplicationCreateRequest.getBaseDocument())
                        .build()
        );

        return id;
    }
}
