package com.bezzangss.sign.application.documents.metadocument.port.in.dto.request;

import com.bezzangss.sign.application.ApplicationException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@Getter
public class MetaDocumentApplicationCreateDocumentRequest {
    private final String metaDocumentId;
    private final String metaDocumentType;
    private final DocumentInMetaDocumentApplicationCreateRequest document;

    @Builder
    public MetaDocumentApplicationCreateDocumentRequest(String metaDocumentId, String metaDocumentType, DocumentInMetaDocumentApplicationCreateRequest document) {
        if (ObjectUtils.isEmpty(metaDocumentId)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "metaDocumentId");
        if (ObjectUtils.isEmpty(metaDocumentType)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "metaDocumentType");
        if (ObjectUtils.isEmpty(document)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "document");

        this.metaDocumentId = metaDocumentId;
        this.metaDocumentType = metaDocumentType;
        this.document = document;
    }
}
