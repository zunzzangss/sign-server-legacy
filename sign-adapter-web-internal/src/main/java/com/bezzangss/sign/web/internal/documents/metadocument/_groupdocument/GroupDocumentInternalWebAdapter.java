package com.bezzangss.sign.web.internal.documents.metadocument._groupdocument;

import com.bezzangss.sign.web.dto.response.WebResponse;
import com.bezzangss.sign.web.internal.documents.metadocument._groupdocument.dto.request.GroupDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument._groupdocument.dto.response.GroupDocumentInternalWebResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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

    @GetMapping(value = "/internal/v1/group-document/{id}")
    public WebResponse<GroupDocumentInternalWebResponse> findById(
            @PathVariable String id,
            @RequestParam(required = false) Set<String> include
    ) {
        return groupDocumentInternalWebHandler.findById(id, include);
    }
}
