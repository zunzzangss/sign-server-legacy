package com.bezzangss.sign.repository.jpa.documents.metadocument.standarddocument;

import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.out.StandardDocumentRepositoryPort;
import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.out.dto.request.StandardDocumentRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.out.dto.response.StandardDocumentRepositoryResponse;
import com.bezzangss.sign.repository.jpa.documents.metadocument.standarddocument.mapper.StandardDocumentJpaMapper;
import com.bezzangss.sign.repository.jpa.documents.metadocument.standarddocument.repository.StandardDocumentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class StandardDocumentJpaAdapter implements StandardDocumentRepositoryPort {
    private final StandardDocumentJpaMapper standardDocumentJpaMapper;
    private final StandardDocumentJpaRepository standardDocumentJpaRepository;

    @Override
    public String create(StandardDocumentRepositoryCreateRequest standardDocumentRepositoryCreateRequest) {
        return standardDocumentJpaRepository.save(standardDocumentJpaMapper.toEntity(standardDocumentRepositoryCreateRequest)).getId();
    }

    @Override
    public Optional<StandardDocumentRepositoryResponse> findById(String id) {
        return standardDocumentJpaRepository.findById(id).map(standardDocumentJpaMapper::toResponse);
    }
}
