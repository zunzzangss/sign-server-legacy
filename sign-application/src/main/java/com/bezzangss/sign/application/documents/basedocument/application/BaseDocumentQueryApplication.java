package com.bezzangss.sign.application.documents.basedocument.application;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.documents.basedocument._templatedocument.application.bridge.TemplateDocumentQueryApplicationBridge;
import com.bezzangss.sign.application.documents.basedocument._templatedocument.port.in.TemplateDocumentQueryApplicationPort;
import com.bezzangss.sign.application.documents.basedocument.application.bridge.BaseDocumentQueryApplicationBridge;
import com.bezzangss.sign.application.documents.basedocument.port.in.BaseDocumentQueryApplicationPort;
import com.bezzangss.sign.common.enums.EnumConverter;
import com.bezzangss.sign.domain.documents.basedocument.BaseDocument;
import com.bezzangss.sign.domain.documents.basedocument.BaseDocumentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.bezzangss.sign.common.exception.ErrorCode.BASE_DOCUMENT_ILLEGAL_TYPE_EXCEPTION;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class BaseDocumentQueryApplication implements BaseDocumentQueryApplicationPort, BaseDocumentQueryApplicationBridge {
    private final TemplateDocumentQueryApplicationPort templateDocumentQueryApplicationPort;
    private final TemplateDocumentQueryApplicationBridge templateDocumentQueryApplicationBridge;

    @Override
    public Optional<String> findResourceIdByIdAndType(String id, String type) {
        switch (EnumConverter.from(BaseDocumentType.class, type)) {
            case TEMPLATE_DOCUMENT:
                return templateDocumentQueryApplicationPort.findResourceIdById(id);
            default:
                throw new ApplicationException(BASE_DOCUMENT_ILLEGAL_TYPE_EXCEPTION, type);
        }
    }

    @Override
    public Optional<? extends BaseDocument> findByIdAndType(String id, String type) {
        switch (EnumConverter.from(BaseDocumentType.class, type)) {
            case TEMPLATE_DOCUMENT:
                return templateDocumentQueryApplicationBridge.findDomainById(id);
            default:
                throw new ApplicationException(BASE_DOCUMENT_ILLEGAL_TYPE_EXCEPTION, type);
        }
    }
}
