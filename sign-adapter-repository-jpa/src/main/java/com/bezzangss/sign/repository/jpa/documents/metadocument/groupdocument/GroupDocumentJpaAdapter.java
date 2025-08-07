package com.bezzangss.sign.repository.jpa.documents.metadocument.groupdocument;

import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.out.GroupDocumentRepositoryPort;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.out.dto.request.GroupDocumentRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.out.dto.response.GroupDocumentRepositoryResponse;
import com.bezzangss.sign.repository.jpa.documents.metadocument.groupdocument.mapper.GroupDocumentJpaMapper;
import com.bezzangss.sign.repository.jpa.documents.metadocument.groupdocument.repository.GroupDocumentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class GroupDocumentJpaAdapter implements GroupDocumentRepositoryPort {
    private final GroupDocumentJpaMapper groupDocumentJpaMapper;
    private final GroupDocumentJpaRepository groupDocumentJpaRepository;

    @Override
    public String create(GroupDocumentRepositoryCreateRequest groupDocumentRepositoryCreateRequest) {
        return groupDocumentJpaRepository.save(groupDocumentJpaMapper.toEntity(groupDocumentRepositoryCreateRequest)).getId();
    }

    @Override
    public Optional<GroupDocumentRepositoryResponse> findById(String id) {
        return groupDocumentJpaRepository.findById(id).map(groupDocumentJpaMapper::toResponse);
    }
}
