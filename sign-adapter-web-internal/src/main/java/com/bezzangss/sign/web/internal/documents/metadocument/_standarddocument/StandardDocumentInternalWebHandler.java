package com.bezzangss.sign.web.internal.documents.metadocument._standarddocument;

import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.in.StandardDocumentCommandApplicationPort;
import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.in.StandardDocumentQueryApplicationPort;
import com.bezzangss.sign.web.dto.response.WebResponse;
import com.bezzangss.sign.web.internal.InternalWebException;
import com.bezzangss.sign.web.internal.documents.metadocument._standarddocument.dto.request.StandardDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument._standarddocument.dto.response.StandardDocumentInternalWebResponse;
import com.bezzangss.sign.web.internal.documents.metadocument._standarddocument.mapper.StandardDocumentInternalWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.bezzangss.sign.common.exception.ErrorCode.STANDARD_DOCUMENT_NOT_FOUND_EXCEPTION;

@RequiredArgsConstructor
@Component
public class StandardDocumentInternalWebHandler {
    private final StandardDocumentInternalWebMapper standardDocumentInternalWebMapper;
    private final StandardDocumentQueryApplicationPort standardDocumentQueryApplicationPort;
    private final StandardDocumentCommandApplicationPort standardDocumentCommandApplicationPort;

    @Transactional
    public WebResponse<StandardDocumentInternalWebResponse> create(StandardDocumentInternalWebCreateRequest standardDocumentInternalWebCreateRequest) {
        String id = standardDocumentCommandApplicationPort.create(standardDocumentInternalWebMapper.toApplicationCreateRequest(standardDocumentInternalWebCreateRequest));
        standardDocumentCommandApplicationPort.process(id);

        StandardDocumentInternalWebResponse standardDocumentInternalWebResponse = standardDocumentQueryApplicationPort.findById(id)
                .map(standardDocumentInternalWebMapper::toResponse)
                .orElseThrow(() -> new InternalWebException(STANDARD_DOCUMENT_NOT_FOUND_EXCEPTION, id));

        return WebResponse.success(standardDocumentInternalWebResponse);
    }
}
