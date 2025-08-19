package com.bezzangss.sign.application.documents.metadocument._standarddocument.application;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.documents.document.port.in.DocumentQueryApplicationPort;
import com.bezzangss.sign.application.documents.metadocument._standarddocument.application.mapper.StandardDocumentApplicationMapper;
import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.in.StandardDocumentQueryApplicationPort;
import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.in.dto.response.StandardDocumentApplicationResponse;
import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.out.StandardDocumentRepositoryPort;
import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.out.dto.response.StandardDocumentRepositoryResponse;
import com.bezzangss.sign.common.enums.EnumConverter;
import com.bezzangss.sign.domain.documents.metadocument.MetaDocumentRelation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class StandardDocumentQueryApplication implements StandardDocumentQueryApplicationPort {
    private final StandardDocumentApplicationMapper standardDocumentApplicationMapper;
    private final StandardDocumentRepositoryPort standardDocumentRepositoryPort;

    private final DocumentQueryApplicationPort documentQueryApplicationPort;

    @Override
    public Optional<StandardDocumentApplicationResponse> findById(String id, Set<String> include) {
        if (ObjectUtils.isEmpty(id)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");

        return standardDocumentRepositoryPort.findById(id).map(standardDocumentRepositoryResponse -> this.toApplicationResponse(standardDocumentRepositoryResponse, include));
    }

    private StandardDocumentApplicationResponse toApplicationResponse(StandardDocumentRepositoryResponse standardDocumentRepositoryResponse, Set<String> include) {
        StandardDocumentApplicationResponse.StandardDocumentApplicationResponseBuilder builder = StandardDocumentApplicationResponse.builder();
        builder.standardDocumentRepositoryResponse(standardDocumentRepositoryResponse);

        if (!ObjectUtils.isEmpty(include)) {
            Set<MetaDocumentRelation> relations = include.stream()
                    .map(relation -> EnumConverter.from(MetaDocumentRelation.class, relation))
                    .collect(Collectors.toSet());

            if (relations.contains(MetaDocumentRelation.DOCUMENT)) {
                builder.document(documentQueryApplicationPort.findByMetaDocumentTypeAndMetaDocumentId("STANDARD_DOCUMENT", standardDocumentRepositoryResponse.getId(), Collections.emptySet()).map(standardDocumentApplicationMapper::toApplicationResponse));
            }
        }

        return builder.build();
    }
}
