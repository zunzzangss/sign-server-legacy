package com.bezzangss.sign.application.resources.resource.port.in.dto.request.common;

import com.bezzangss.sign.application.ApplicationException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@Getter
public class ResourceApplicationCreateByIdRequest {
    private final String id;

    @Builder
    public ResourceApplicationCreateByIdRequest(String id) {
        if (ObjectUtils.isEmpty(id)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");

        this.id = id;
    }
}
