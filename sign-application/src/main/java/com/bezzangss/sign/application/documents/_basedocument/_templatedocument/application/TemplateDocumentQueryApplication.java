package com.bezzangss.sign.application.documents._basedocument._templatedocument.application;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.documents._basedocument._templatedocument.application.mapper.TemplateDocumentApplicationMapper;
import com.bezzangss.sign.application.documents._basedocument._templatedocument.port.in.TemplateDocumentQueryApplicationPort;
import com.bezzangss.sign.application.documents._basedocument._templatedocument.port.in.dto.response.TemplateDocumentApplicationResponse;
import com.bezzangss.sign.application.documents._basedocument._templatedocument.port.out.TemplateDocumentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class TemplateDocumentQueryApplication implements TemplateDocumentQueryApplicationPort {
    private final TemplateDocumentRepositoryPort templateDocumentRepositoryOutPort;

    private final TemplateDocumentApplicationMapper templateDocumentServiceMapper;

    @Override
    public Optional<TemplateDocumentApplicationResponse> findById(String id) {
        if (ObjectUtils.isEmpty(id)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");

        return templateDocumentRepositoryOutPort.findById(id).map(templateDocumentServiceMapper::toApplicationResponse);
    }
}
