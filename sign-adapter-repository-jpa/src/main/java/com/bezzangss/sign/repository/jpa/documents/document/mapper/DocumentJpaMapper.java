package com.bezzangss.sign.repository.jpa.documents.document.mapper;

import com.bezzangss.sign.application.documents.document.port.out.dto.request.DocumentRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.document.port.out.dto.request.DocumentRepositoryUpdateRequest;
import com.bezzangss.sign.application.documents.document.port.out.dto.response.DocumentRepositoryResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.repository.jpa.documents.document.entity.DocumentJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = CommonMapperConfigurer.class)
public interface DocumentJpaMapper extends CommonMapper {
    DocumentJpaEntity toEntity(DocumentRepositoryCreateRequest documentRepositoryCreateRequest);

    DocumentRepositoryResponse toResponse(DocumentJpaEntity documentJpaEntity);

    void update(DocumentRepositoryUpdateRequest documentRepositoryUpdateRequest, @MappingTarget DocumentJpaEntity documentJpaEntity);
}
