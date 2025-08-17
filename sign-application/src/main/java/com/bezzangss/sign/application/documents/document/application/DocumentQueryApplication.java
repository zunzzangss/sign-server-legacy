package com.bezzangss.sign.application.documents.document.application;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.documents.document.application.bridge.DocumentQueryApplicationBridge;
import com.bezzangss.sign.application.documents.document.application.mapper.DocumentApplicationMapper;
import com.bezzangss.sign.application.documents.document.port.in.DocumentQueryApplicationPort;
import com.bezzangss.sign.application.documents.document.port.out.DocumentRepositoryPort;
import com.bezzangss.sign.domain.documents.document.aggregate.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class DocumentQueryApplication implements DocumentQueryApplicationPort, DocumentQueryApplicationBridge {
    private final DocumentApplicationMapper documentApplicationMapper;
    private final DocumentRepositoryPort documentRepositoryPort;

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
}
