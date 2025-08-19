package com.bezzangss.sign.application.documents.associate.publisher.port.in;

import com.bezzangss.sign.application.documents.associate.publisher.port.in.dto.response.PublisherApplicationResponse;

import java.util.Optional;

public interface PublisherQueryApplicationPort {
    Optional<PublisherApplicationResponse> findByDocumentId(String documentId);
}
