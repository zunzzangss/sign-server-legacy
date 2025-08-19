package com.bezzangss.sign.application.documents.metadocument._groupdocument.application;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.documents.document.port.in.DocumentQueryApplicationPort;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.application.mapper.GroupDocumentApplicationMapper;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.in.GroupDocumentQueryApplicationPort;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.in.dto.response.GroupDocumentApplicationResponse;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.out.GroupDocumentRepositoryPort;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.out.dto.response.GroupDocumentRepositoryResponse;
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
public class GroupDocumentQueryApplication implements GroupDocumentQueryApplicationPort {
    private final GroupDocumentApplicationMapper groupDocumentApplicationMapper;
    private final GroupDocumentRepositoryPort groupDocumentRepositoryPort;

    private final DocumentQueryApplicationPort documentQueryApplicationPort;

    @Override
    public Optional<GroupDocumentApplicationResponse> findById(String id, Set<String> include) {
        if (ObjectUtils.isEmpty(id)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");

        return groupDocumentRepositoryPort.findById(id).map(groupDocumentRepositoryResponse -> this.toApplicationResponse(groupDocumentRepositoryResponse, include));
    }

    private GroupDocumentApplicationResponse toApplicationResponse(GroupDocumentRepositoryResponse groupDocumentRepositoryResponse, Set<String> include) {
        GroupDocumentApplicationResponse.GroupDocumentApplicationResponseBuilder builder = GroupDocumentApplicationResponse.builder();
        builder.groupDocumentRepositoryResponse(groupDocumentRepositoryResponse);

        if (!ObjectUtils.isEmpty(include)) {
            Set<MetaDocumentRelation> relations = include.stream()
                    .map(relation -> EnumConverter.from(MetaDocumentRelation.class, relation))
                    .collect(Collectors.toSet());

            if (relations.contains(MetaDocumentRelation.DOCUMENT)) {
                builder.documents(
                        documentQueryApplicationPort.findAllByMetaDocumentTypeAndMetaDocumentId("GROUP_DOCUMENT", groupDocumentRepositoryResponse.getId(), Collections.emptySet()).stream()
                                .map(groupDocumentApplicationMapper::toApplicationResponse)
                                .collect(Collectors.toList())
                );
            }
        }

        return builder.build();
    }
}
