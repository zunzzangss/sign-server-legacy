package com.bezzangss.sign.domain.documents.associate.signer.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class SignerDomainCreateRequest {
    private String id;
    private String name;
    private String email;
    private String cellPhone;
    private int order;
    private String documentId;
    private Instant createdAt;
    private Instant lastModifiedAt;
}
