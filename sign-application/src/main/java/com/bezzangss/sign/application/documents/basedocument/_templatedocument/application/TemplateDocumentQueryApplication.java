package com.bezzangss.sign.application.documents.basedocument._templatedocument.application;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.documents.basedocument._templatedocument.application.bridge.TemplateDocumentQueryApplicationBridge;
import com.bezzangss.sign.application.documents.basedocument._templatedocument.application.mapper.TemplateDocumentApplicationMapper;
import com.bezzangss.sign.application.documents.basedocument._templatedocument.port.in.TemplateDocumentQueryApplicationPort;
import com.bezzangss.sign.application.documents.basedocument._templatedocument.port.in.dto.response.TemplateDocumentApplicationResponse;
import com.bezzangss.sign.application.documents.basedocument._templatedocument.port.out.TemplateDocumentRepositoryPort;
import com.bezzangss.sign.application.resources.resource.port.in.ResourceQueryApplicationPort;
import com.bezzangss.sign.application.resources.resource.port.in.dto.response.ResourceApplicationResponse;
import com.bezzangss.sign.application.resources.resourcereference.port.in.ResourceReferenceQueryApplicationPort;
import com.bezzangss.sign.application.resources.resourcereference.port.in.dto.response.ResourceReferenceApplicationResponse;
import com.bezzangss.sign.domain.documents.basedocument.templatedocument.aggregate.TemplateDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class TemplateDocumentQueryApplication implements TemplateDocumentQueryApplicationPort, TemplateDocumentQueryApplicationBridge {
    private final TemplateDocumentApplicationMapper templateDocumentServiceMapper;
    private final TemplateDocumentRepositoryPort templateDocumentRepositoryPort;

    private final ResourceQueryApplicationPort resourceQueryApplicationPort;
    private final ResourceReferenceQueryApplicationPort resourceReferenceQueryApplicationPort;

    @Override
    public Optional<TemplateDocumentApplicationResponse> findById(String id) {
        if (ObjectUtils.isEmpty(id)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");

        return templateDocumentRepositoryPort.findById(id).map(templateDocumentServiceMapper::toApplicationResponse);
    }

    @Override
    public Optional<String> findResourceIdById(String id) {
        if (ObjectUtils.isEmpty(id)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");

        return resourceReferenceQueryApplicationPort.findByDomainIdAndDomain(id, "TEMPLATE_DOCUMENT")
                .map(ResourceReferenceApplicationResponse::getResourceId)
                .flatMap(resourceQueryApplicationPort::findById)
                .map(ResourceApplicationResponse::getId);
    }

    @Override
    public Optional<TemplateDocument> findDomainById(String id) {
        if (ObjectUtils.isEmpty(id)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");

        return templateDocumentRepositoryPort.findById(id).map(templateDocumentServiceMapper::toDomain);
    }
}
