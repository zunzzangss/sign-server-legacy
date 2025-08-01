package com.bezzangss.sign.web.internal.resources.resource.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Setter
@Getter
public class ResourceInternalWebCreateByMultipartFileRequest {
    private MultipartFile file;
}
