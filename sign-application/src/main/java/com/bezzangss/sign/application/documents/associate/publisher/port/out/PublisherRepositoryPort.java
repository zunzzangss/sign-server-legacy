package com.bezzangss.sign.application.documents.associate.publisher.port.out;

import com.bezzangss.sign.application.documents.associate.publisher.port.out.dto.request.PublisherRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.associate.publisher.port.out.dto.response.PublisherRepositoryResponse;

import java.util.Optional;

public interface PublisherRepositoryPort {
    String create(PublisherRepositoryCreateRequest publisherRepositoryCreateRequest);

    Optional<PublisherRepositoryResponse> findById(String id);
}
