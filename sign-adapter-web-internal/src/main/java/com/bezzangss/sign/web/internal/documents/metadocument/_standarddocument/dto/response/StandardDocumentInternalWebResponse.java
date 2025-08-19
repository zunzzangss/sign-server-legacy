package com.bezzangss.sign.web.internal.documents.metadocument._standarddocument.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.Instant;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Setter
@Getter
public class StandardDocumentInternalWebResponse {
    private String id;
    private String name;
    private String description;
    private Instant createdAt;
    private Instant lastModifiedAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Optional<Document> document;

    @Builder
    @Getter
    public static class Document {
        private final String id;
    }
}
