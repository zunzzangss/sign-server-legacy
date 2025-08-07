package com.bezzangss.sign.application.documents.associate.publisher.port.out.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class PublisherRepositoryResponse {
    private final String id;
    private final String name;
    private final String email;
    private final String cellPhone;
    private final String documentId;
    private final Instant createdAt;
    private final Instant lastModifiedAt;
}
