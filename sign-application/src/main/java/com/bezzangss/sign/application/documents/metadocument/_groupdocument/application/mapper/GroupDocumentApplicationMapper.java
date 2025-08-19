package com.bezzangss.sign.application.documents.metadocument._groupdocument.application.mapper;

import com.bezzangss.sign.application.documents.document.port.in.dto.response.DocumentApplicationResponse;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.in.dto.response.GroupDocumentApplicationResponse;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.out.dto.request.GroupDocumentRepositoryCreateRequest;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.domain.documents.metadocument.groupdocument.aggregate.GroupDocument;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface GroupDocumentApplicationMapper extends CommonMapper {
    GroupDocumentRepositoryCreateRequest toRepositoryCreateRequest(GroupDocument groupDocument);

    GroupDocumentApplicationResponse.Document toApplicationResponse(DocumentApplicationResponse documentApplicationResponse);
}
