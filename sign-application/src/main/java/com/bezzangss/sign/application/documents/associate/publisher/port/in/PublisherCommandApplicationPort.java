package com.bezzangss.sign.application.documents.associate.publisher.port.in;

import com.bezzangss.sign.application.documents.associate.publisher.port.in.dto.request.PublisherApplicationCreateRequest;

public interface PublisherCommandApplicationPort {
    String create(PublisherApplicationCreateRequest publisherApplicationCreateRequest);
}
