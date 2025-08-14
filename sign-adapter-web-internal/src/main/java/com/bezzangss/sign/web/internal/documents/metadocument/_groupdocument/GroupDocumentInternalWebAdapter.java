package com.bezzangss.sign.web.internal.documents.metadocument._groupdocument;

import com.bezzangss.sign.web.dto.response.WebResponse;
import com.bezzangss.sign.web.internal.documents.metadocument._groupdocument.dto.request.GroupDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument._groupdocument.dto.response.GroupDocumentInternalWebResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GroupDocumentInternalWebAdapter {
    private final GroupDocumentInternalWebHandler groupDocumentInternalWebHandler;

    @PostMapping(value = "/internal/v1/group-document/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<GroupDocumentInternalWebResponse> create(
            @RequestBody GroupDocumentInternalWebCreateRequest groupDocumentInternalWebCreateRequest
    ) {
        return groupDocumentInternalWebHandler.create(groupDocumentInternalWebCreateRequest);
    }
}
