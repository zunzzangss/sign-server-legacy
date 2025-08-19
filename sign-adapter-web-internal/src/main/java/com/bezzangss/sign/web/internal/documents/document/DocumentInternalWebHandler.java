package com.bezzangss.sign.web.internal.documents.document;

import com.bezzangss.sign.application.documents.document.port.in.DocumentQueryApplicationPort;
import com.bezzangss.sign.web.dto.response.WebResponse;
import com.bezzangss.sign.web.internal.InternalWebException;
import com.bezzangss.sign.web.internal.documents.document.dto.response.DocumentInternalWebResponse;
import com.bezzangss.sign.web.internal.documents.document.mapper.DocumentInternalWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static com.bezzangss.sign.common.exception.ErrorCode.DOCUMENT_NOT_FOUND_EXCEPTION;

@RequiredArgsConstructor
@Component
public class DocumentInternalWebHandler {
    private final DocumentInternalWebMapper documentInternalWebMapper;
    private final DocumentQueryApplicationPort documentQueryApplicationPort;

    @Transactional(readOnly = true)
    public WebResponse<DocumentInternalWebResponse> findById(String id, Set<String> include) {
        DocumentInternalWebResponse documentInternalWebResponse = documentQueryApplicationPort.findById(id, include)
                .map(documentInternalWebMapper::toResponse)
                .orElseThrow(() -> new InternalWebException(DOCUMENT_NOT_FOUND_EXCEPTION, id));

        return WebResponse.success(documentInternalWebResponse);
    }
}
