package com.bezzangss.sign.web.internal.documents.metadocument._groupdocument;

import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.in.GroupDocumentCommandApplicationPort;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.in.GroupDocumentQueryApplicationPort;
import com.bezzangss.sign.web.dto.response.WebResponse;
import com.bezzangss.sign.web.internal.InternalWebException;
import com.bezzangss.sign.web.internal.documents.metadocument._groupdocument.dto.request.GroupDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument._groupdocument.dto.response.GroupDocumentInternalWebResponse;
import com.bezzangss.sign.web.internal.documents.metadocument._groupdocument.mapper.GroupDocumentInternalWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.bezzangss.sign.common.exception.ErrorCode.GROUP_DOCUMENT_NOT_FOUND_EXCEPTION;

@RequiredArgsConstructor
@Component
public class GroupDocumentInternalWebHandler {
    private final GroupDocumentInternalWebMapper groupDocumentInternalWebMapper;
    private final GroupDocumentQueryApplicationPort groupDocumentQueryApplicationPort;
    private final GroupDocumentCommandApplicationPort groupDocumentCommandApplicationPort;

    @Transactional
    public WebResponse<GroupDocumentInternalWebResponse> create(GroupDocumentInternalWebCreateRequest groupDocumentInternalWebCreateRequest) {
        String id = groupDocumentCommandApplicationPort.create(groupDocumentInternalWebMapper.toApplicationCreateRequest(groupDocumentInternalWebCreateRequest));

        GroupDocumentInternalWebResponse groupDocumentInternalWebResponse = groupDocumentQueryApplicationPort.findById(id)
                .map(groupDocumentInternalWebMapper::toResponse)
                .orElseThrow(() -> new InternalWebException(GROUP_DOCUMENT_NOT_FOUND_EXCEPTION, id));

        return WebResponse.success(groupDocumentInternalWebResponse);
    }
}
