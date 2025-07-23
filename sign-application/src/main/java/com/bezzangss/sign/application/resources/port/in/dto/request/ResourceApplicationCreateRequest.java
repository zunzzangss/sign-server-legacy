package com.bezzangss.sign.application.resources.port.in.dto.request;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.common.resource.ResourceStream;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@Builder
@Getter
public class ResourceApplicationCreateRequest {
    private final String id;
    private final String type;
    private final String source;
    private final ResourceStream resourceStream;

    @Builder
    public ResourceApplicationCreateRequest(String id, String type, String source, ResourceStream resourceStream) {
        if (ObjectUtils.isEmpty(type)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "type");
        if (ObjectUtils.isEmpty(resourceStream)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "resourceStream");

        this.id = id;
        this.type = type;
        this.source = source;
        this.resourceStream = resourceStream;
    }
}
