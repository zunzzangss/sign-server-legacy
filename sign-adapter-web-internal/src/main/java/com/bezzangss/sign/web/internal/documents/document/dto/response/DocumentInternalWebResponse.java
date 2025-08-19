package com.bezzangss.sign.web.internal.documents.document.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Setter
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

    @Builder
    @Getter
    public static class Publisher {
        private final String id;
    }

    @Builder
    @Getter
    public static class Signer {
        private final String id;
    }

    @Builder
    @Getter
    public static class Cc {
        private final String id;
    }
}
