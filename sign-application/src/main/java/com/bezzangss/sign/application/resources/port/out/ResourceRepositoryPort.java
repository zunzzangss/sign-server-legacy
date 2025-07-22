package com.bezzangss.sign.application.resources.port.out;

import com.bezzangss.sign.application.resources.port.out.dto.request.ResourceRepositoryCreateRequest;

public interface ResourceRepositoryPort {
    String create(ResourceRepositoryCreateRequest resourceRepositoryCreateRequest);
}
