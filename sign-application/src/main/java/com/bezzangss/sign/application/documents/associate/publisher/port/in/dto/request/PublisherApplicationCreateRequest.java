package com.bezzangss.sign.application.documents.associate.publisher.port.in.dto.request;

import com.bezzangss.sign.application.ApplicationException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@Getter
public class PublisherApplicationCreateRequest {
    private final String name;
    private final String email;
    private final String cellPhone;
    private final String documentId;

    @Builder
    public PublisherApplicationCreateRequest(String name, String email, String cellPhone, String documentId) {
        if (ObjectUtils.isEmpty(name)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "name");
        if (ObjectUtils.isEmpty(documentId)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "documentId");

        this.name = name;
        this.email = email;
        this.cellPhone = cellPhone;
        this.documentId = documentId;
    }
}
