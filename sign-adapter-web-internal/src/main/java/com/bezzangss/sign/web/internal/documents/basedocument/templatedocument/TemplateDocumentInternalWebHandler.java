package com.bezzangss.sign.web.internal.documents.basedocument.templatedocument;

import com.bezzangss.sign.application.documents._basedocument._templatedocument.port.in.TemplateDocumentCommandApplicationPort;
import com.bezzangss.sign.application.documents._basedocument._templatedocument.port.in.TemplateDocumentQueryApplicationPort;
import com.bezzangss.sign.web.dto.response.WebResponse;
import com.bezzangss.sign.web.internal.InternalWebException;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.dto.request.TemplateDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.dto.response.TemplateDocumentInternalWebResponse;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.mapper.TemplateDocumentInternalWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.bezzangss.sign.common.exception.ErrorCode.TEMPLATE_DOCUMENT_NOT_FOUND_EXCEPTION;

@RequiredArgsConstructor
@Component
public class TemplateDocumentInternalWebHandler {
    private final TemplateDocumentInternalWebMapper templateDocumentInternalWebMapper;
    private final TemplateDocumentQueryApplicationPort templateDocumentQueryApplicationPort;
    private final TemplateDocumentCommandApplicationPort templateDocumentCommandApplicationPort;

    @Transactional
    public WebResponse<TemplateDocumentInternalWebResponse> create(TemplateDocumentInternalWebCreateRequest templateDocumentInternalWebCreateRequest) {
        String id = templateDocumentCommandApplicationPort.create(templateDocumentInternalWebMapper.toApplicationCreateRequest(templateDocumentInternalWebCreateRequest));

        TemplateDocumentInternalWebResponse templateDocumentInternalWebResponse = templateDocumentQueryApplicationPort.findById(id)
                .map(templateDocumentInternalWebMapper::toResponse)
                .orElseThrow(() -> new InternalWebException(TEMPLATE_DOCUMENT_NOT_FOUND_EXCEPTION, id));

        return WebResponse.success(templateDocumentInternalWebResponse);
    }
}
