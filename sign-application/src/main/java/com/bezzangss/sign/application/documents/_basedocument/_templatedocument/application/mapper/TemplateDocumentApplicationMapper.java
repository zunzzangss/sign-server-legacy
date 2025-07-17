package com.bezzangss.sign.application.documents._basedocument._templatedocument.application.mapper;

import com.bezzangss.sign.application.documents._basedocument._templatedocument.port.in.dto.response.TemplateDocumentApplicationResponse;
import com.bezzangss.sign.application.documents._basedocument._templatedocument.port.out.dto.request.TemplateDocumentRepositoryCreateRequest;
import com.bezzangss.sign.application.documents._basedocument._templatedocument.port.out.dto.response.TemplateDocumentRepositoryResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.domain.documents._basedocument._templatedocument.aggregate.TemplateDocument;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface TemplateDocumentApplicationMapper extends CommonMapper {
    TemplateDocumentRepositoryCreateRequest toRepositoryCreateRequest(TemplateDocument templateDocument);

    TemplateDocumentApplicationResponse toApplicationResponse(TemplateDocumentRepositoryResponse templateDocumentRepositoryResponse);
}
