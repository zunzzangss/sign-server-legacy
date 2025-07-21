package com.bezzangss.sign.repositoryjpa.documents._basedocument._templatedocument.mapper;

import com.bezzangss.sign.application.documents._basedocument._templatedocument.port.out.dto.request.TemplateDocumentRepositoryCreateRequest;
import com.bezzangss.sign.application.documents._basedocument._templatedocument.port.out.dto.response.TemplateDocumentRepositoryResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.repositoryjpa.documents._basedocument._templatedocument.entity.TemplateDocumentJpaEntity;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface TemplateDocumentJpaMapper extends CommonMapper {
    TemplateDocumentJpaEntity toEntity(TemplateDocumentRepositoryCreateRequest templateDocumentRepositoryCreateRequest);

    TemplateDocumentRepositoryResponse toResponse(TemplateDocumentJpaEntity templateDocumentJpaEntity);
}
