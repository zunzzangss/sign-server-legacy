package com.bezzangss.sign.application.documents.associate.signer.port.in.dto.request;

import com.bezzangss.sign.application.ApplicationException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@Getter
public class SignerApplicationCreateRequest {
    private final String name;
    private final String email;
    private final String cellPhone;
    private final int order;
    private final String documentId;

    @Builder
    public SignerApplicationCreateRequest(String name, String email, String cellPhone, int order, String documentId) {
        if (ObjectUtils.isEmpty(name)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "name");
        if (ObjectUtils.isEmpty(documentId)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "documentId");

        this.name = name;
        this.email = email;
        this.cellPhone = cellPhone;
        this.order = order;
        this.documentId = documentId;
    }
}
