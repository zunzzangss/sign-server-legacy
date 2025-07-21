package com.bezzangss.sign.repositoryjpa.documents._basedocument._templatedocument;

import com.bezzangss.sign.application.documents._basedocument._templatedocument.port.out.TemplateDocumentRepositoryPort;
import com.bezzangss.sign.application.documents._basedocument._templatedocument.port.out.dto.request.TemplateDocumentRepositoryCreateRequest;
import com.bezzangss.sign.application.documents._basedocument._templatedocument.port.out.dto.response.TemplateDocumentRepositoryResponse;
import com.bezzangss.sign.repositoryjpa.documents._basedocument._templatedocument.mapper.TemplateDocumentJpaMapper;
import com.bezzangss.sign.repositoryjpa.documents._basedocument._templatedocument.repository.TemplateDocumentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class TemplateDocumentJpaAdapter implements TemplateDocumentRepositoryPort {
    private final TemplateDocumentJpaRepository templateDocumentJpaRepository;

    private final TemplateDocumentJpaMapper templateDocumentJpaMapper;

    @Override
    public String create(TemplateDocumentRepositoryCreateRequest templateDocumentRepositoryCreateRequest) {
        return templateDocumentJpaRepository.save(templateDocumentJpaMapper.toEntity(templateDocumentRepositoryCreateRequest)).getId();
    }

    @Override
    public Optional<TemplateDocumentRepositoryResponse> findById(String id) {
        return templateDocumentJpaRepository.findById(id).map(templateDocumentJpaMapper::toResponse);
    }
}
