package com.bezzangss.sign.web.internal.resources.resource;

import com.bezzangss.sign.web.dto.response.WebResponse;
import com.bezzangss.sign.web.internal.resources.resource.dto.request.ResourceInternalWebCreateByMultipartFileRequest;
import com.bezzangss.sign.web.internal.resources.resource.dto.response.ResourceInternalWebResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ResourceInternalWebAdapter {
    private final ResourceInternalWebHandler resourceInternalWebHandler;

    @PostMapping(value = "/internal/v1/resource/create-by-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public WebResponse<ResourceInternalWebResponse> createByMultipartFile(
            @ModelAttribute ResourceInternalWebCreateByMultipartFileRequest resourceInternalWebCreateByMultipartFileRequest
    ) {
        return resourceInternalWebHandler.createByMultipartFile(resourceInternalWebCreateByMultipartFileRequest);
    }
}
