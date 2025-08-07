package com.bezzangss.sign.repository.jpa.documents.metadocument.standarddocument.mapper;

import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.out.dto.request.StandardDocumentRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.out.dto.response.StandardDocumentRepositoryResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.repository.jpa.documents.metadocument.standarddocument.entity.StandardDocumentJpaEntity;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface StandardDocumentJpaMapper extends CommonMapper {
    StandardDocumentJpaEntity toEntity(StandardDocumentRepositoryCreateRequest standardDocumentRepositoryCreateRequest);

    StandardDocumentRepositoryResponse toResponse(StandardDocumentJpaEntity standardDocumentJpaEntity);
}
