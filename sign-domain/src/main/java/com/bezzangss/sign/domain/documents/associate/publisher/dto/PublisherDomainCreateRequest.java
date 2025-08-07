package com.bezzangss.sign.domain.documents.associate.publisher.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class PublisherDomainCreateRequest {
    private final String id;
    private final String name;
    private final String email;
    private final String cellPhone;
    private final String documentId;
    private final Instant createdAt;
    private final Instant lastModifiedAt;
}
