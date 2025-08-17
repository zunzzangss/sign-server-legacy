package com.bezzangss.sign.repository.jpa.documents.document;

import com.bezzangss.sign.application.documents.document.port.out.DocumentRepositoryPort;
import com.bezzangss.sign.application.documents.document.port.out.dto.request.DocumentRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.document.port.out.dto.request.DocumentRepositoryUpdateRequest;
import com.bezzangss.sign.application.documents.document.port.out.dto.response.DocumentRepositoryResponse;
import com.bezzangss.sign.repository.jpa.JpaRepositoryException;
import com.bezzangss.sign.repository.jpa.documents.document.entity.DocumentJpaEntity;
import com.bezzangss.sign.repository.jpa.documents.document.mapper.DocumentJpaMapper;
import com.bezzangss.sign.repository.jpa.documents.document.repository.DocumentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.bezzangss.sign.common.exception.ErrorCode.DOCUMENT_NOT_FOUND_EXCEPTION;

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
    public String update(DocumentRepositoryUpdateRequest documentRepositoryUpdateRequest) {
        DocumentJpaEntity documentJpaEntity = documentJpaRepository.findById(documentRepositoryUpdateRequest.getId()).orElseThrow(() -> new JpaRepositoryException(DOCUMENT_NOT_FOUND_EXCEPTION, documentRepositoryUpdateRequest.getId()));
        documentJpaMapper.update(documentRepositoryUpdateRequest, documentJpaEntity);

        return documentJpaRepository.save(documentJpaEntity).getId();
    }

    @Override
    public Optional<DocumentRepositoryResponse> findById(String id) {
        return documentJpaRepository.findById(id).map(documentJpaMapper::toResponse);
    }

    @Override
    public List<DocumentRepositoryResponse> findAllByMetaDocumentTypeAndMetaDocumentId(String metaDocumentType, String metaDocumentId) {
        return documentJpaRepository.findAllByMetaDocumentTypeAndMetaDocumentId(metaDocumentType, metaDocumentId).stream()
                .map(documentJpaMapper::toResponse)
                .collect(Collectors.toList());
    }
}
