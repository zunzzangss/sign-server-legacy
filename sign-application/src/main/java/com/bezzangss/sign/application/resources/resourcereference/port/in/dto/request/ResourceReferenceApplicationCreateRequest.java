package com.bezzangss.sign.application.resources.resourcereference.port.in.dto.request;

import com.bezzangss.sign.application.ApplicationException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@Getter
public class ResourceReferenceApplicationCreateRequest {
    private final String domainId;
    private final String domain;
    private final String resourceId;

    @Builder
    public ResourceReferenceApplicationCreateRequest(String domainId, String domain, String resourceId) {
        if (ObjectUtils.isEmpty(domainId)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "domainId");
        if (ObjectUtils.isEmpty(domain)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "domain");
        if (ObjectUtils.isEmpty(resourceId)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "resourceId");

        this.domainId = domainId;
        this.domain = domain;
        this.resourceId = resourceId;
    }
}
