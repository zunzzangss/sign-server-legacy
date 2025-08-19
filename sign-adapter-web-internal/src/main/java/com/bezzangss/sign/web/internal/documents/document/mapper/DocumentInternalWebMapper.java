package com.bezzangss.sign.web.internal.documents.document.mapper;

import com.bezzangss.sign.application.documents.document.port.in.dto.response.DocumentApplicationResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.web.internal.documents.document.dto.response.DocumentInternalWebResponse;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(config = CommonMapperConfigurer.class)
public interface DocumentInternalWebMapper extends CommonMapper {
    DocumentInternalWebResponse toResponse(DocumentApplicationResponse documentApplicationResponse);

    DocumentInternalWebResponse.Publisher toResponse(DocumentApplicationResponse.Publisher publisher);

    default Optional<DocumentInternalWebResponse.Publisher> toResponse(Optional<DocumentApplicationResponse.Publisher> publisher) {
        return publisher == null ? null : publisher.map(this::toResponse);
    }
}
