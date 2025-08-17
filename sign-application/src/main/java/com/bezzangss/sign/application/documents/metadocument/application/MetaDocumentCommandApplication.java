package com.bezzangss.sign.application.documents.metadocument.application;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.documents.basedocument.port.in.BaseDocumentQueryApplicationPort;
import com.bezzangss.sign.application.documents.document.application.bridge.DocumentQueryApplicationBridge;
import com.bezzangss.sign.application.documents.document.port.in.DocumentCommandApplicationPort;
import com.bezzangss.sign.application.documents.document.port.in.DocumentQueryApplicationPort;
import com.bezzangss.sign.application.documents.metadocument.application.mapper.MetaDocumentApplicationMapper;
import com.bezzangss.sign.application.documents.metadocument.port.in.MetaDocumentCommandApplicationPort;
import com.bezzangss.sign.application.documents.metadocument.port.in.dto.request.MetaDocumentApplicationCreateDocumentRequest;
import com.bezzangss.sign.application.documents.metadocument.port.in.dto.request.MetaDocumentApplicationReplicateResourceByBaseDocumentRequest;
import com.bezzangss.sign.application.resources.resource.port.in.ResourceCommandApplicationPort;
import com.bezzangss.sign.application.resources.resourcereference.port.in.ResourceReferenceCommandApplicationPort;
import com.bezzangss.sign.application.resources.resourcereference.port.in.dto.request.ResourceReferenceApplicationCreateRequest;
import com.bezzangss.sign.domain.documents.document.aggregate.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.bezzangss.sign.common.exception.ErrorCode.RESOURCE_NOT_FOUND_EXCEPTION;

@RequiredArgsConstructor
@Transactional
@Component
public class MetaDocumentCommandApplication implements MetaDocumentCommandApplicationPort {
    private final MetaDocumentApplicationMapper metaDocumentApplicationMapper;

    private final BaseDocumentQueryApplicationPort baseDocumentQueryApplicationPort;

    private final DocumentQueryApplicationPort documentQueryApplicationPort;
    private final DocumentCommandApplicationPort documentCommandApplicationPort;
    private final DocumentQueryApplicationBridge documentQueryApplicationBridge;

    private final ResourceCommandApplicationPort resourceCommandApplicationPort;
    private final ResourceReferenceCommandApplicationPort resourceReferenceCommandApplicationPort;

    @Override
    public String replicateResourceByBaseDocument(MetaDocumentApplicationReplicateResourceByBaseDocumentRequest metaDocumentApplicationReplicateResourceByBaseDocumentRequest) {
        String baseDocumentId = metaDocumentApplicationReplicateResourceByBaseDocumentRequest.getBaseDocument().getId();
        String baseDocumentType = metaDocumentApplicationReplicateResourceByBaseDocumentRequest.getBaseDocument().getType();
        String baseDocumentResourceId = baseDocumentQueryApplicationPort.findResourceIdByIdAndType(baseDocumentId, baseDocumentType).orElseThrow(() -> new ApplicationException(RESOURCE_NOT_FOUND_EXCEPTION, String.format("%s %s", baseDocumentId, baseDocumentType)));
        String resourceId = resourceCommandApplicationPort.replicate(baseDocumentResourceId);
        resourceReferenceCommandApplicationPort.create(
                ResourceReferenceApplicationCreateRequest.builder()
                        .domainId(metaDocumentApplicationReplicateResourceByBaseDocumentRequest.getMetaDocumentId())
                        .domain(metaDocumentApplicationReplicateResourceByBaseDocumentRequest.getMetaDocumentType())
                        .resourceId(resourceId)
                        .build()
        );

        return resourceId;
    }

    @Override
    public String createDocument(MetaDocumentApplicationCreateDocumentRequest metaDocumentApplicationCreateDocumentRequest) {
        return documentCommandApplicationPort.create(
                metaDocumentApplicationMapper.toApplicationCreateRequest(
                        metaDocumentApplicationCreateDocumentRequest.getDocument(),
                        metaDocumentApplicationCreateDocumentRequest.getMetaDocumentType(),
                        metaDocumentApplicationCreateDocumentRequest.getMetaDocumentId()
                )
        );
    }

    @Override
    public void process(String metaDocumentType, String metaDocumentId) {
        for (Document document : documentQueryApplicationBridge.findAllDomainByMetaDocumentTypeAndMetaDocumentId(metaDocumentType, metaDocumentId)) {
            documentCommandApplicationPort.process(document.getId());
        }
    }
}
