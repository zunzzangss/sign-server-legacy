package com.bezzangss.sign.web.internal.resources.resource.dto.response;

import lombok.*;

import java.time.Instant;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Setter
@Getter
public class ResourceInternalWebResponse {
    private String id;
    private String type;
    private String source;
    private long size;
    private Instant createdAt;
    private Instant lastModifiedAt;
}
