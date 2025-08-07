package com.bezzangss.sign.application.documents.metadocument.port.in.dto.request;

import com.bezzangss.sign.application.ApplicationException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@Getter
public class MetaDocumentApplicationReplicateResourceByBaseDocumentRequest {
    private final String metaDocumentId;
    private final String metaDocumentType;
    private final BaseDocumentInMetaDocumentApplicationCreateRequest baseDocument;

    @Builder
    public MetaDocumentApplicationReplicateResourceByBaseDocumentRequest(String metaDocumentId, String metaDocumentType, BaseDocumentInMetaDocumentApplicationCreateRequest baseDocument) {
        if (ObjectUtils.isEmpty(metaDocumentId)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "metaDocumentId");
        if (ObjectUtils.isEmpty(metaDocumentType)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "metaDocumentType");
        if (ObjectUtils.isEmpty(baseDocument)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "baseDocument");

        this.metaDocumentId = metaDocumentId;
        this.metaDocumentType = metaDocumentType;
        this.baseDocument = baseDocument;
    }
}
