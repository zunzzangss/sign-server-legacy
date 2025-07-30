package com.bezzangss.sign.application.resources.resource.port.out;

import com.bezzangss.sign.application.resources.resource.port.out.dto.request.ResourceRepositoryCreateRequest;
import com.bezzangss.sign.application.resources.resource.port.out.dto.response.ResourceRepositoryResponse;

import java.util.Optional;

public interface ResourceRepositoryPort {
    String create(ResourceRepositoryCreateRequest resourceRepositoryCreateRequest);

    Optional<ResourceRepositoryResponse> findById(String id);
}
