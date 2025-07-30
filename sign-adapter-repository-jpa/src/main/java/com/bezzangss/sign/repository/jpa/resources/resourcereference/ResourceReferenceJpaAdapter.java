package com.bezzangss.sign.repository.jpa.resources.resourcereference;

import com.bezzangss.sign.application.resources.resourcereference.port.out.ResourceReferenceRepositoryPort;
import com.bezzangss.sign.application.resources.resourcereference.port.out.dto.request.ResourceReferenceRepositoryCreateRequest;
import com.bezzangss.sign.application.resources.resourcereference.port.out.dto.response.ResourceReferenceRepositoryResponse;
import com.bezzangss.sign.repository.jpa.resources.resourcereference.mapper.ResourceReferenceJpaMapper;
import com.bezzangss.sign.repository.jpa.resources.resourcereference.repository.ResourceReferenceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class ResourceReferenceJpaAdapter implements ResourceReferenceRepositoryPort {
    private final ResourceReferenceJpaMapper resourceReferenceJpaMapper;
    private final ResourceReferenceJpaRepository resourceReferenceJpaRepository;

    @Override
    public String create(ResourceReferenceRepositoryCreateRequest resourceReferenceRepositoryCreateRequest) {
        return resourceReferenceJpaRepository.save(resourceReferenceJpaMapper.toEntity(resourceReferenceRepositoryCreateRequest)).getId();
    }

    @Override
    public Optional<ResourceReferenceRepositoryResponse> findByDomainIdAndDomain(String domainId, String domain) {
        return resourceReferenceJpaRepository.findByDomainIdAndDomain(domainId, domain).map(resourceReferenceJpaMapper::toResponse);
    }

    @Override
    public List<ResourceReferenceRepositoryResponse> findAllByDomainIdAndDomain(String domainId, String domain) {
        return resourceReferenceJpaRepository.findAllByDomainIdAndDomain(domainId, domain).stream()
                .map(resourceReferenceJpaMapper::toResponse)
                .collect(Collectors.toList());
    }
}
