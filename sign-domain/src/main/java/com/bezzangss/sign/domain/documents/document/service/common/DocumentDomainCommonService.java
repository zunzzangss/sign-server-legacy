package com.bezzangss.sign.domain.documents.document.service.common;

import com.bezzangss.sign.domain.DomainException;
import com.bezzangss.sign.domain.documents.document.aggregate.Document;
import org.springframework.stereotype.Component;

import static com.bezzangss.sign.common.exception.ErrorCode.DOCUMENT_STATUS_IS_NOT_PROCESSING_EXCEPTION;

@Component
public class DocumentDomainCommonService {
    public void validateProcessing(Document document) {
        if (!document.isProcessing()) throw new DomainException(DOCUMENT_STATUS_IS_NOT_PROCESSING_EXCEPTION, document.getId());
    }
}
