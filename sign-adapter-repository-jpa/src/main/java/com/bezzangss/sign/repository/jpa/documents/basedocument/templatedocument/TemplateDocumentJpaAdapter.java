package com.bezzangss.sign.repository.jpa.documents.basedocument.templatedocument;

import com.bezzangss.sign.application.documents.basedocument._templatedocument.port.out.TemplateDocumentRepositoryPort;
import com.bezzangss.sign.application.documents.basedocument._templatedocument.port.out.dto.request.TemplateDocumentRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.basedocument._templatedocument.port.out.dto.response.TemplateDocumentRepositoryResponse;
import com.bezzangss.sign.repository.jpa.documents.basedocument.templatedocument.mapper.TemplateDocumentJpaMapper;
import com.bezzangss.sign.repository.jpa.documents.basedocument.templatedocument.repository.TemplateDocumentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class TemplateDocumentJpaAdapter implements TemplateDocumentRepositoryPort {
    private final TemplateDocumentJpaMapper templateDocumentJpaMapper;
    private final TemplateDocumentJpaRepository templateDocumentJpaRepository;

    @Override
    public String create(TemplateDocumentRepositoryCreateRequest templateDocumentRepositoryCreateRequest) {
        return templateDocumentJpaRepository.save(templateDocumentJpaMapper.toEntity(templateDocumentRepositoryCreateRequest)).getId();
    }

    @Override
    public Optional<TemplateDocumentRepositoryResponse> findById(String id) {
        return templateDocumentJpaRepository.findById(id).map(templateDocumentJpaMapper::toResponse);
    }
}
