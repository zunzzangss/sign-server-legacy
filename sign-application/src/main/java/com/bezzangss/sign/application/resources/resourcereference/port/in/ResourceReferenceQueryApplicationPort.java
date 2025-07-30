package com.bezzangss.sign.application.resources.resourcereference.port.in;

import com.bezzangss.sign.application.resources.resourcereference.port.in.dto.response.ResourceReferenceApplicationResponse;

import java.util.List;
import java.util.Optional;

public interface ResourceReferenceQueryApplicationPort {
    Optional<ResourceReferenceApplicationResponse> findByDomainIdAndDomain(String domainId, String domain);

    List<ResourceReferenceApplicationResponse> findAllByDomainIdAndDomain(String domainId, String domain);
}
