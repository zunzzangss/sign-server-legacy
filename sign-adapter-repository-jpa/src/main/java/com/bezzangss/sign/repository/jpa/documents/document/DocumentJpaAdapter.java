package com.bezzangss.sign.repository.jpa.documents.document;

import com.bezzangss.sign.application.documents.document.port.out.DocumentRepositoryPort;
import com.bezzangss.sign.application.documents.document.port.out.dto.request.DocumentRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.document.port.out.dto.response.DocumentRepositoryResponse;
import com.bezzangss.sign.repository.jpa.documents.document.mapper.DocumentJpaMapper;
import com.bezzangss.sign.repository.jpa.documents.document.repository.DocumentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class DocumentJpaAdapter implements DocumentRepositoryPort {
    private final DocumentJpaMapper documentJpaMapper;
    private final DocumentJpaRepository documentJpaRepository;

    @Override
    public String create(DocumentRepositoryCreateRequest documentRepositoryCreateRequest) {
        return documentJpaRepository.save(documentJpaMapper.toEntity(documentRepositoryCreateRequest)).getId();
    }

    @Override
    public Optional<DocumentRepositoryResponse> findById(String id) {
        return documentJpaRepository.findById(id).map(documentJpaMapper::toResponse);
    }
}
