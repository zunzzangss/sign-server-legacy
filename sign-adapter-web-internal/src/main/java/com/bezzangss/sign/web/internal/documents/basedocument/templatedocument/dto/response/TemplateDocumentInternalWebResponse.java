package com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.dto.response;

import lombok.*;

import java.time.Instant;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Setter
@Getter
public class TemplateDocumentInternalWebResponse {
    private String id;
    private String name;
    private String description;
    private String status;
    private Instant createdAt;
    private Instant lastModifiedAt;
}
