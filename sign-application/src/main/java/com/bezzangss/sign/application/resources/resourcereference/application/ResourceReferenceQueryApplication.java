package com.bezzangss.sign.application.resources.resourcereference.application;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.resources.resourcereference.application.mapper.ResourceReferenceApplicationMapper;
import com.bezzangss.sign.application.resources.resourcereference.port.in.ResourceReferenceQueryApplicationPort;
import com.bezzangss.sign.application.resources.resourcereference.port.in.dto.response.ResourceReferenceApplicationResponse;
import com.bezzangss.sign.application.resources.resourcereference.port.out.ResourceReferenceRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class ResourceReferenceQueryApplication implements ResourceReferenceQueryApplicationPort {
    private final ResourceReferenceApplicationMapper resourceReferenceApplicationMapper;
    private final ResourceReferenceRepositoryPort resourceReferenceRepositoryPort;

    @Override
    public Optional<ResourceReferenceApplicationResponse> findByDomainIdAndDomain(String domainId, String domain) {
        if (ObjectUtils.isEmpty(domainId)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "domainId");
        if (ObjectUtils.isEmpty(domain)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "domain");

        return resourceReferenceRepositoryPort.findByDomainIdAndDomain(domainId, domain).map(resourceReferenceApplicationMapper::toApplicationResponse);
    }

    @Override
    public List<ResourceReferenceApplicationResponse> findAllByDomainIdAndDomain(String domainId, String domain) {
        if (ObjectUtils.isEmpty(domainId)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "domainId");
        if (ObjectUtils.isEmpty(domain)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "domain");

        return resourceReferenceRepositoryPort.findAllByDomainIdAndDomain(domainId, domain).stream()
                .map(resourceReferenceApplicationMapper::toApplicationResponse)
                .collect(Collectors.toList());
    }
}
