package com.bezzangss.sign.repository.jpa.documents.associate.publisher;

import com.bezzangss.sign.application.documents.associate.publisher.port.out.PublisherRepositoryPort;
import com.bezzangss.sign.application.documents.associate.publisher.port.out.dto.request.PublisherRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.associate.publisher.port.out.dto.response.PublisherRepositoryResponse;
import com.bezzangss.sign.repository.jpa.documents.associate.publisher.mapper.PublisherJpaMapper;
import com.bezzangss.sign.repository.jpa.documents.associate.publisher.repository.PublisherJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PublisherJpaAdapter implements PublisherRepositoryPort {
    private final PublisherJpaMapper publisherJpaMapper;
    private final PublisherJpaRepository publisherJpaRepository;

    @Override
    public String create(PublisherRepositoryCreateRequest publisherRepositoryCreateRequest) {
        return publisherJpaRepository.save(publisherJpaMapper.toEntity(publisherRepositoryCreateRequest)).getId();
    }

    @Override
    public Optional<PublisherRepositoryResponse> findById(String id) {
        return publisherJpaRepository.findById(id).map(publisherJpaMapper::toResponse);
    }
}
