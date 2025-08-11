package com.bezzangss.sign.web.internal.documents.metadocument._standarddocument;

import com.bezzangss.sign.web.dto.response.WebResponse;
import com.bezzangss.sign.web.internal.documents.metadocument._standarddocument.dto.request.StandardDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument._standarddocument.dto.response.StandardDocumentInternalWebResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StandardDocumentInternalWebAdapter {
    private final StandardDocumentInternalWebHandler standardDocumentInternalWebHandler;

    @PostMapping(value = "/internal/v1/standard-document/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<StandardDocumentInternalWebResponse> create(
            @RequestBody StandardDocumentInternalWebCreateRequest standardDocumentInternalWebCreateRequest
    ) {
        return standardDocumentInternalWebHandler.create(standardDocumentInternalWebCreateRequest);
    }
}
