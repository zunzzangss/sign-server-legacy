package com.bezzangss.sign.application.documents.metadocument.application.mapper;

import com.bezzangss.sign.application.documents.document.port.in.dto.request.DocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents.metadocument.port.in.dto.request.DocumentInMetaDocumentApplicationCreateRequest;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface MetaDocumentApplicationMapper extends CommonMapper {
    DocumentApplicationCreateRequest toApplicationCreateRequest(DocumentInMetaDocumentApplicationCreateRequest documentInMetaDocumentApplicationCreateRequest, String metaDocumentType, String metaDocumentId);
}
