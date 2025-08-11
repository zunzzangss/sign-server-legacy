package com.bezzangss.sign.web.internal.documents.metadocument._standarddocument.mapper;

import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.in.dto.request.StandardDocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.in.dto.response.StandardDocumentApplicationResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.web.internal.documents.metadocument._standarddocument.dto.request.StandardDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument._standarddocument.dto.response.StandardDocumentInternalWebResponse;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface StandardDocumentInternalWebMapper extends CommonMapper {
    StandardDocumentInternalWebResponse toResponse(StandardDocumentApplicationResponse standardDocumentApplicationResponse);

    StandardDocumentApplicationCreateRequest toApplicationCreateRequest(StandardDocumentInternalWebCreateRequest standardDocumentInternalWebCreateRequest);
}
