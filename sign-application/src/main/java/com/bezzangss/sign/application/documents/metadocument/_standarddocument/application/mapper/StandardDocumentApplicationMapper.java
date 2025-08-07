package com.bezzangss.sign.application.documents.metadocument._standarddocument.application.mapper;

import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.in.dto.response.StandardDocumentApplicationResponse;
import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.out.dto.request.StandardDocumentRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.out.dto.response.StandardDocumentRepositoryResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.domain.documents.metadocument.standarddocument.aggregate.StandardDocument;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface StandardDocumentApplicationMapper extends CommonMapper {
    StandardDocumentRepositoryCreateRequest toRepositoryCreateRequest(StandardDocument standardDocument);

    StandardDocumentApplicationResponse toApplicationResponse(StandardDocumentRepositoryResponse standardDocumentRepositoryResponse);
}
