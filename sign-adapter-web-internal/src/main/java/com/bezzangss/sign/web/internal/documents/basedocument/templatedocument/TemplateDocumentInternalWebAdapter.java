package com.bezzangss.sign.web.internal.documents.basedocument.templatedocument;

import com.bezzangss.sign.web.dto.response.WebResponse;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.dto.request.TemplateDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.dto.response.TemplateDocumentInternalWebResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TemplateDocumentInternalWebAdapter {
    private final TemplateDocumentInternalWebHandler templateDocumentInternalWebHandler;

    @PostMapping(value = "/internal/v1/template-document/create", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WebResponse<TemplateDocumentInternalWebResponse> create(
            @RequestBody TemplateDocumentInternalWebCreateRequest templateDocumentInternalWebCreateRequest
    ) {
        return templateDocumentInternalWebHandler.create(templateDocumentInternalWebCreateRequest);
    }
}
