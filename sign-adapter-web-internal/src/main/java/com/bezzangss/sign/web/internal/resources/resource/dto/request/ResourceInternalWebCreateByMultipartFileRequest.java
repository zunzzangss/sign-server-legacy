package com.bezzangss.sign.web.internal.resources.resource.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ResourceInternalWebCreateByMultipartFileRequest {
    private MultipartFile file;
}
