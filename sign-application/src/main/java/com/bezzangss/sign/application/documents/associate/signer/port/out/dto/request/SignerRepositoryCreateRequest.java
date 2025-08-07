package com.bezzangss.sign.application.documents.associate.signer.port.out.dto.request;

import com.bezzangss.sign.domain.documents.associate.signer.SignerStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class SignerRepositoryCreateRequest {
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
