package com.bezzangss.sign.application.documents.basedocument._templatedocument.application;

import com.bezzangss.sign.application.documents.basedocument._templatedocument.application.bridge.TemplateDocumentCommandApplicationBridge;
import com.bezzangss.sign.application.documents.basedocument._templatedocument.application.mapper.TemplateDocumentApplicationMapper;
import com.bezzangss.sign.application.documents.basedocument._templatedocument.port.in.TemplateDocumentCommandApplicationPort;
import com.bezzangss.sign.application.documents.basedocument._templatedocument.port.in.dto.request.TemplateDocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents.basedocument._templatedocument.port.out.TemplateDocumentRepositoryPort;
import com.bezzangss.sign.application.resources.resourcereference.port.in.ResourceReferenceCommandApplicationPort;
import com.bezzangss.sign.application.resources.resourcereference.port.in.dto.request.ResourceReferenceApplicationCreateRequest;
import com.bezzangss.sign.domain.documents.basedocument.templatedocument.aggregate.TemplateDocument;
import com.bezzangss.sign.domain.documents.basedocument.templatedocument.dto.TemplateDocumentDomainCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Component
public class TemplateDocumentCommandApplication implements TemplateDocumentCommandApplicationPort, TemplateDocumentCommandApplicationBridge {
    private final TemplateDocumentApplicationMapper templateDocumentServiceMapper;
    private final TemplateDocumentRepositoryPort templateDocumentRepositoryPort;

    private final ResourceReferenceCommandApplicationPort resourceReferenceCommandApplicationPort;

    @Override
    public String create(TemplateDocumentApplicationCreateRequest templateDocumentApplicationCreateRequest) {
        TemplateDocument templateDocument = TemplateDocument.create(
                TemplateDocumentDomainCreateRequest.builder()
                        .name(templateDocumentApplicationCreateRequest.getName())
                        .description(templateDocumentApplicationCreateRequest.getDescription())
                        .build()
        );
        String id = templateDocumentRepositoryPort.create(templateDocumentServiceMapper.toRepositoryCreateRequest(templateDocument));

        Optional.ofNullable(templateDocumentApplicationCreateRequest.getResource())
                .ifPresent(resource -> resourceReferenceCommandApplicationPort.create(
                                ResourceReferenceApplicationCreateRequest.builder()
                                        .domainId(id)
                                        .domain("TEMPLATE_DOCUMENT")
                                        .resourceId(resource.getId())
                                        .build()
                        )
                );

        return id;
    }
}
