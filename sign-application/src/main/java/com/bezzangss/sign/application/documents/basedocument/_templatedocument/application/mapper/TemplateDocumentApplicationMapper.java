package com.bezzangss.sign.application.documents.basedocument._templatedocument.application.mapper;

import com.bezzangss.sign.application.documents.basedocument._templatedocument.port.in.dto.response.TemplateDocumentApplicationResponse;
import com.bezzangss.sign.application.documents.basedocument._templatedocument.port.out.dto.request.TemplateDocumentRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.basedocument._templatedocument.port.out.dto.response.TemplateDocumentRepositoryResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.domain.documents.basedocument.templatedocument.aggregate.TemplateDocument;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface TemplateDocumentApplicationMapper extends CommonMapper {
    TemplateDocument toDomain(TemplateDocumentRepositoryResponse templateDocumentRepositoryResponse);

    TemplateDocumentRepositoryCreateRequest toRepositoryCreateRequest(TemplateDocument templateDocument);

    TemplateDocumentApplicationResponse toApplicationResponse(TemplateDocumentRepositoryResponse templateDocumentRepositoryResponse);
}
