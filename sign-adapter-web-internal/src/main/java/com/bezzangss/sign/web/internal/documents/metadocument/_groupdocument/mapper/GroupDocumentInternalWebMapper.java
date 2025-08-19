package com.bezzangss.sign.web.internal.documents.metadocument._groupdocument.mapper;

import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.in.dto.request.GroupDocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.in.dto.response.GroupDocumentApplicationResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.web.internal.documents.metadocument._groupdocument.dto.request.GroupDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument._groupdocument.dto.response.GroupDocumentInternalWebResponse;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(config = CommonMapperConfigurer.class)
public interface GroupDocumentInternalWebMapper extends CommonMapper {
    GroupDocumentApplicationCreateRequest toApplicationCreateRequest(GroupDocumentInternalWebCreateRequest groupDocumentInternalWebCreateRequest);

    GroupDocumentInternalWebResponse toResponse(GroupDocumentApplicationResponse groupDocumentApplicationResponse);

    GroupDocumentInternalWebResponse.Document toResponse(GroupDocumentApplicationResponse.Document document);

    default Optional<GroupDocumentInternalWebResponse.Document> toResponse(Optional<GroupDocumentApplicationResponse.Document> document) {
        return document == null ? null : document.map(this::toResponse);
    }
}
