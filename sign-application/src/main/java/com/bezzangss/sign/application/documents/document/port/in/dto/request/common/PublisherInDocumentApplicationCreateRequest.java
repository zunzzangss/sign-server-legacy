package com.bezzangss.sign.application.documents.document.port.in.dto.request.common;

import com.bezzangss.sign.application.ApplicationException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@Getter
public class PublisherInDocumentApplicationCreateRequest {
    private final String name;
    private final String email;
    private final String cellPhone;

    @Builder
    public PublisherInDocumentApplicationCreateRequest(String name, String email, String cellPhone) {
        if (ObjectUtils.isEmpty(name)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "name");

        this.name = name;
        this.email = email;
        this.cellPhone = cellPhone;
    }
}
