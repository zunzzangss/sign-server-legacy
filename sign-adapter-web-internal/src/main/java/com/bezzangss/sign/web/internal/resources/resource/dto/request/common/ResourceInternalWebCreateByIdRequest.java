package com.bezzangss.sign.web.internal.resources.resource.dto.request.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ResourceInternalWebCreateByIdRequest {
    private String id;
}
