package com.bezzangss.sign.web.internal.documents.metadocument._groupdocument.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class GroupDocumentInternalWebResponse {
    private String id;
    private String name;
    private String description;
    private Instant createdAt;
    private Instant lastModifiedAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Document> documents;

    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @Getter
    public static class Document {
        private String id;
    }
}
