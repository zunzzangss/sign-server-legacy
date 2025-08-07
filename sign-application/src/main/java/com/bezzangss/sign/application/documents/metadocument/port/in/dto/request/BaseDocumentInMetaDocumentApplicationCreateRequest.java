package com.bezzangss.sign.application.documents.metadocument.port.in.dto.request;

import com.bezzangss.sign.application.ApplicationException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@Getter
public class BaseDocumentInMetaDocumentApplicationCreateRequest {
    private final String id;
    private final String type;

    @Builder
    public BaseDocumentInMetaDocumentApplicationCreateRequest(String id, String type) {
        if (ObjectUtils.isEmpty(id)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");
        if (ObjectUtils.isEmpty(type)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "type");

        this.id = id;
        this.type = type;
    }
}
