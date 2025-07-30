package com.bezzangss.sign.repository.jpa.resources.resource;

import com.bezzangss.sign.application.resources.resource.port.out.ResourceRepositoryPort;
import com.bezzangss.sign.application.resources.resource.port.out.dto.request.ResourceRepositoryCreateRequest;
import com.bezzangss.sign.application.resources.resource.port.out.dto.response.ResourceRepositoryResponse;
import com.bezzangss.sign.repository.jpa.resources.resource.mapper.ResourceJpaMapper;
import com.bezzangss.sign.repository.jpa.resources.resource.repository.ResourceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ResourceJpaAdapter implements ResourceRepositoryPort {
    private final ResourceJpaMapper resourceJpaMapper;
    private final ResourceJpaRepository resourceJpaRepository;

    @Override
    public String create(ResourceRepositoryCreateRequest resourceRepositoryCreateRequest) {
        return resourceJpaRepository.save(resourceJpaMapper.toEntity(resourceRepositoryCreateRequest)).getId();
    }

    @Override
    public Optional<ResourceRepositoryResponse> findById(String id) {
        return resourceJpaRepository.findById(id).map(resourceJpaMapper::toResponse);
    }
}
