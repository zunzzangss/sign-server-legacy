package com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.mapper;

import com.bezzangss.sign.application.documents.basedocument._templatedocument.port.in.dto.request.TemplateDocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents.basedocument._templatedocument.port.in.dto.response.TemplateDocumentApplicationResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.dto.request.TemplateDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.dto.response.TemplateDocumentInternalWebResponse;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface TemplateDocumentInternalWebMapper extends CommonMapper {
    TemplateDocumentApplicationCreateRequest toApplicationCreateRequest(TemplateDocumentInternalWebCreateRequest templateDocumentInternalWebCreateRequest);

    TemplateDocumentInternalWebResponse toResponse(TemplateDocumentApplicationResponse templateDocumentApplicationResponse);
}
