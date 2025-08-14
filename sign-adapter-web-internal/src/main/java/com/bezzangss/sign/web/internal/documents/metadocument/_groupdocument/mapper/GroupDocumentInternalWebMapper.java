package com.bezzangss.sign.web.internal.documents.metadocument._groupdocument.mapper;

import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.in.dto.request.GroupDocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.in.dto.response.GroupDocumentApplicationResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.web.internal.documents.metadocument._groupdocument.dto.request.GroupDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument._groupdocument.dto.response.GroupDocumentInternalWebResponse;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface GroupDocumentInternalWebMapper extends CommonMapper {
    GroupDocumentInternalWebResponse toResponse(GroupDocumentApplicationResponse groupDocumentApplicationResponse);

    GroupDocumentApplicationCreateRequest toApplicationCreateRequest(GroupDocumentInternalWebCreateRequest groupDocumentInternalWebCreateRequest);
}
