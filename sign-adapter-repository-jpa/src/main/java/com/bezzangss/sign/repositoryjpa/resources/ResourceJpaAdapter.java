package com.bezzangss.sign.repositoryjpa.resources;

import com.bezzangss.sign.application.resources.port.out.ResourceRepositoryPort;
import com.bezzangss.sign.application.resources.port.out.dto.request.ResourceRepositoryCreateRequest;
import com.bezzangss.sign.application.resources.port.out.dto.response.ResourceRepositoryResponse;
import com.bezzangss.sign.repositoryjpa.resources.mapper.ResourceJpaMapper;
import com.bezzangss.sign.repositoryjpa.resources.repository.ResourceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ResourceJpaAdapter implements ResourceRepositoryPort {
    private final ResourceJpaRepository resourceJpaRepository;

    private final ResourceJpaMapper resourceJpaMapper;

    @Override
    public String create(ResourceRepositoryCreateRequest resourceRepositoryCreateRequest) {
        return resourceJpaRepository.save(resourceJpaMapper.toEntity(resourceRepositoryCreateRequest)).getId();
    }

    @Override
    public Optional<ResourceRepositoryResponse> findById(String id) {
        return resourceJpaRepository.findById(id).map(resourceJpaMapper::toResponse);
    }
}
