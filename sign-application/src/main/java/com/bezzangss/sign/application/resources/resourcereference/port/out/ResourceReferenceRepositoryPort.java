package com.bezzangss.sign.application.resources.resourcereference.port.out;

import com.bezzangss.sign.application.resources.resourcereference.port.out.dto.request.ResourceReferenceRepositoryCreateRequest;
import com.bezzangss.sign.application.resources.resourcereference.port.out.dto.response.ResourceReferenceRepositoryResponse;

import java.util.List;
import java.util.Optional;

public interface ResourceReferenceRepositoryPort {
    String create(ResourceReferenceRepositoryCreateRequest resourceReferenceRepositoryCreateRequest);

    Optional<ResourceReferenceRepositoryResponse> findByDomainIdAndDomain(String domainId, String domain);

    List<ResourceReferenceRepositoryResponse> findAllByDomainIdAndDomain(String domainId, String domain);
}
