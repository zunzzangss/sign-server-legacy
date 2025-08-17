package com.bezzangss.sign.web.internal.documents.associate.signer.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class SignerInternalWebResponse {
    private final String id;
    private final String name;
    private final String email;
    private final String cellPhone;
    private final int order;
    private final String status;
    private final String documentId;
    private final Instant createdAt;
    private final Instant lastModifiedAt;
}
