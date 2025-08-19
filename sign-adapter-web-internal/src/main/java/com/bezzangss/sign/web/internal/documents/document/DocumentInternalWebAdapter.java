package com.bezzangss.sign.web.internal.documents.document;

import com.bezzangss.sign.web.dto.response.WebResponse;
import com.bezzangss.sign.web.internal.documents.document.dto.response.DocumentInternalWebResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequiredArgsConstructor
@RestController
public class DocumentInternalWebAdapter {
    private final DocumentInternalWebHandler documentInternalWebHandler;

    @GetMapping(value = "/internal/v1/document/{id}")
    public WebResponse<DocumentInternalWebResponse> findById(
            @PathVariable String id,
            @RequestParam(required = false) Set<String> include
    ) {
        return documentInternalWebHandler.findById(id, include);
    }
}
