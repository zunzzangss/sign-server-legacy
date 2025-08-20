package com.bezzangss.sign.web.internal.documents.document.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class DocumentInternalWebResponse {
    private String id;
    private String name;
    private String description;
    private String status;
    private String metaDocumentType;
    private String metaDocumentId;
    private Instant createdAt;
    private Instant lastModifiedAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Optional<Publisher> publisher;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Signer> signers;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Cc> ccs;

    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @Getter
    public static class Publisher {
        private String id;
    }

    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @Getter
    public static class Signer {
        private String id;
    }

    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @Getter
    public static class Cc {
        private String id;
    }
}
