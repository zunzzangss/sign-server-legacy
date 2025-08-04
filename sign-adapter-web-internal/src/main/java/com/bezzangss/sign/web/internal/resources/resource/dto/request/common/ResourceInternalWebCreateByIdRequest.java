package com.bezzangss.sign.web.internal.resources.resource.dto.request.common;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Setter
@Getter
public class ResourceInternalWebCreateByIdRequest {
    private String id;
}
