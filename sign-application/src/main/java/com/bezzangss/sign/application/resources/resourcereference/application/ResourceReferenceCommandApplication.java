package com.bezzangss.sign.application.resources.resourcereference.application;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.documents.basedocument._templatedocument.port.in.TemplateDocumentQueryApplicationPort;
import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.in.StandardDocumentQueryApplicationPort;
import com.bezzangss.sign.application.resources.resource.port.in.ResourceQueryApplicationPort;
import com.bezzangss.sign.application.resources.resourcereference.application.mapper.ResourceReferenceApplicationMapper;
import com.bezzangss.sign.application.resources.resourcereference.port.in.ResourceReferenceCommandApplicationPort;
import com.bezzangss.sign.application.resources.resourcereference.port.in.dto.request.ResourceReferenceApplicationCreateRequest;
import com.bezzangss.sign.application.resources.resourcereference.port.out.ResourceReferenceRepositoryPort;
import com.bezzangss.sign.common.enums.EnumConverter;
import com.bezzangss.sign.domain.resources.resourcereference.ResourceReferenceDomain;
import com.bezzangss.sign.domain.resources.resourcereference.aggregate.ResourceReference;
import com.bezzangss.sign.domain.resources.resourcereference.dto.ResourceReferenceCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.bezzangss.sign.common.exception.ErrorCode.*;

@RequiredArgsConstructor
@Transactional
@Component
public class ResourceReferenceCommandApplication implements ResourceReferenceCommandApplicationPort {
    private final ResourceReferenceApplicationMapper resourceReferenceApplicationMapper;
    private final ResourceReferenceRepositoryPort resourceReferenceRepositoryPort;

    private final ResourceQueryApplicationPort resourceQueryApplicationPort;

    private final TemplateDocumentQueryApplicationPort templateDocumentQueryApplicationPort;

    private final StandardDocumentQueryApplicationPort standardDocumentQueryApplicationPort;

    @Override
    public String create(ResourceReferenceApplicationCreateRequest resourceReferenceApplicationCreateRequest) {
        ResourceReference resourceReference = ResourceReference.create(
                ResourceReferenceCreateRequest.builder()
                        .domainId(resourceReferenceApplicationCreateRequest.getDomainId())
                        .domain(EnumConverter.from(ResourceReferenceDomain.class, resourceReferenceApplicationCreateRequest.getDomain()))
                        .resourceId(resourceReferenceApplicationCreateRequest.getResourceId())
                        .build()
        );
        this.validate(resourceReference);

        return resourceReferenceRepositoryPort.create(resourceReferenceApplicationMapper.toRepositoryCreateRequest(resourceReference));
    }

    private void validate(ResourceReference resourceReference) {
        String domainId = resourceReference.getDomainId();
        ResourceReferenceDomain domain = resourceReference.getDomain();
        String resourceId = resourceReference.getResourceId();

        switch (domain) {
            case TEMPLATE_DOCUMENT:
                if (!templateDocumentQueryApplicationPort.findById(domainId).isPresent()) throw new ApplicationException(TEMPLATE_DOCUMENT_NOT_FOUND_EXCEPTION, domainId);
                break;
            case STANDARD_DOCUMENT:
                if (!standardDocumentQueryApplicationPort.findById(domainId).isPresent()) throw new ApplicationException(STANDARD_DOCUMENT_NOT_FOUND_EXCEPTION, domainId);
                break;
            default:
                throw new ApplicationException(RESOURCE_REFERENCE_ILLEGAL_TYPE_INTERNAL_SERVER_ERROR, domain.name());
        }

        if (!resourceQueryApplicationPort.findById(resourceId).isPresent()) throw new ApplicationException(RESOURCE_NOT_FOUND_EXCEPTION, resourceId);
    }
}
