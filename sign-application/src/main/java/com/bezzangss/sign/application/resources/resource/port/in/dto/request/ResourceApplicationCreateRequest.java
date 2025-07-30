package com.bezzangss.sign.application.resources.resource.port.in.dto.request;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.common.inputstream.InputStreamHandler;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@Getter
public class ResourceApplicationCreateRequest {
    private final String type;
    private final InputStreamHandler inputStreamHandler;

    @Builder
    public ResourceApplicationCreateRequest(String type, InputStreamHandler inputStreamHandler) {
        if (ObjectUtils.isEmpty(type)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "type");
        if (ObjectUtils.isEmpty(inputStreamHandler)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "inputStreamHandler");

        this.type = type;
        this.inputStreamHandler = inputStreamHandler;
    }
}
