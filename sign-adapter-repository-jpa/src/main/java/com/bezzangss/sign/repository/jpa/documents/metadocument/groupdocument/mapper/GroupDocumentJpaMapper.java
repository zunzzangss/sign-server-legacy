package com.bezzangss.sign.repository.jpa.documents.metadocument.groupdocument.mapper;

import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.out.dto.request.GroupDocumentRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.out.dto.response.GroupDocumentRepositoryResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.repository.jpa.documents.metadocument.groupdocument.entity.GroupDocumentJpaEntity;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface GroupDocumentJpaMapper extends CommonMapper {
    GroupDocumentJpaEntity toEntity(GroupDocumentRepositoryCreateRequest groupDocumentRepositoryCreateRequest);

    GroupDocumentRepositoryResponse toResponse(GroupDocumentJpaEntity groupDocumentJpaEntity);
}
