package com.bezzangss.sign.application.documents.document.application;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.documents.associate.cc.port.in.CcQueryApplicationPort;
import com.bezzangss.sign.application.documents.associate.publisher.port.in.PublisherQueryApplicationPort;
import com.bezzangss.sign.application.documents.associate.signer.port.in.SignerApplicationQueryPort;
import com.bezzangss.sign.application.documents.document.application.bridge.DocumentQueryApplicationBridge;
import com.bezzangss.sign.application.documents.document.application.mapper.DocumentApplicationMapper;
import com.bezzangss.sign.application.documents.document.port.in.DocumentQueryApplicationPort;
import com.bezzangss.sign.application.documents.document.port.in.dto.response.DocumentApplicationResponse;
import com.bezzangss.sign.application.documents.document.port.out.DocumentRepositoryPort;
import com.bezzangss.sign.application.documents.document.port.out.dto.response.DocumentRepositoryResponse;
import com.bezzangss.sign.common.enums.EnumConverter;
import com.bezzangss.sign.domain.documents.document.DocumentRelation;
import com.bezzangss.sign.domain.documents.document.aggregate.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class DocumentQueryApplication implements DocumentQueryApplicationPort, DocumentQueryApplicationBridge {
    private final DocumentApplicationMapper documentApplicationMapper;
    private final DocumentRepositoryPort documentRepositoryPort;

    private final PublisherQueryApplicationPort publisherQueryApplicationPort;
    private final SignerApplicationQueryPort signerApplicationQueryPort;
    private final CcQueryApplicationPort ccQueryApplicationPort;

    @Override
    public Optional<DocumentApplicationResponse> findById(String id, Set<String> include) {
        if (ObjectUtils.isEmpty(id)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");

        return documentRepositoryPort.findById(id).map(documentRepositoryResponse -> this.toApplicationResponse(documentRepositoryResponse, include));
    }

    @Override
    public Optional<DocumentApplicationResponse> findByMetaDocumentTypeAndMetaDocumentId(String metaDocumentType, String metaDocumentId, Set<String> include) {
        if (ObjectUtils.isEmpty(metaDocumentType)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "metaDocumentType");
        if (ObjectUtils.isEmpty(metaDocumentId)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "metaDocumentId");

        return documentRepositoryPort.findByMetaDocumentTypeAndMetaDocumentId(metaDocumentType, metaDocumentId).map(documentRepositoryResponse -> this.toApplicationResponse(documentRepositoryResponse, include));
    }

    @Override
    public List<DocumentApplicationResponse> findAllByMetaDocumentTypeAndMetaDocumentId(String metaDocumentType, String metaDocumentId, Set<String> include) {
        if (ObjectUtils.isEmpty(metaDocumentType)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "metaDocumentType");
        if (ObjectUtils.isEmpty(metaDocumentId)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "metaDocumentId");

        return documentRepositoryPort.findAllByMetaDocumentTypeAndMetaDocumentId(metaDocumentType, metaDocumentId).stream()
                .map(documentRepositoryResponse -> this.toApplicationResponse(documentRepositoryResponse, include))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Document> findDomainById(String id) {
        if (ObjectUtils.isEmpty(id)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");

        return documentRepositoryPort.findById(id).map(documentApplicationMapper::toDomain);
    }

    @Override
    public List<Document> findAllDomainByMetaDocumentTypeAndMetaDocumentId(String metaDocumentType, String metaDocumentId) {
        return documentRepositoryPort.findAllByMetaDocumentTypeAndMetaDocumentId(metaDocumentType, metaDocumentId).stream()
                .map(documentApplicationMapper::toDomain)
                .collect(Collectors.toList());
    }

    private DocumentApplicationResponse toApplicationResponse(DocumentRepositoryResponse documentRepositoryResponse, Set<String> include) {
        DocumentApplicationResponse.DocumentApplicationResponseBuilder builder = DocumentApplicationResponse.builder();
        builder.documentRepositoryResponse(documentRepositoryResponse);

        if (!ObjectUtils.isEmpty(include)) {
            Set<DocumentRelation> relations = include.stream()
                    .map(relation -> EnumConverter.from(DocumentRelation.class, relation))
                    .collect(Collectors.toSet());

            if (relations.contains(DocumentRelation.PUBLISHER)) {
                builder.publisher(publisherQueryApplicationPort.findByDocumentId(documentRepositoryResponse.getId()).map(documentApplicationMapper::toApplicationResponse));
            }

            if (relations.contains(DocumentRelation.SIGNER)) {
                builder.signers(
                        signerApplicationQueryPort.findAllByDocumentId(documentRepositoryResponse.getId()).stream()
                                .map(documentApplicationMapper::toApplicationResponse)
                                .collect(Collectors.toList())
                );
            }

            if (relations.contains(DocumentRelation.CC)) {
                builder.ccs(
                        ccQueryApplicationPort.findAllByDocumentId(documentRepositoryResponse.getId()).stream()
                                .map(documentApplicationMapper::toApplicationResponse)
                                .collect(Collectors.toList())
                );
            }
        }

        return builder.build();
    }
}
