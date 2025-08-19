package com.bezzangss.sign.application.documents.associate.publisher.application;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.documents.associate.publisher.application.mapper.PublisherApplicationMapper;
import com.bezzangss.sign.application.documents.associate.publisher.port.in.PublisherQueryApplicationPort;
import com.bezzangss.sign.application.documents.associate.publisher.port.in.dto.response.PublisherApplicationResponse;
import com.bezzangss.sign.application.documents.associate.publisher.port.out.PublisherRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class PublisherQueryApplication implements PublisherQueryApplicationPort {
    private final PublisherApplicationMapper publisherApplicationMapper;
    private final PublisherRepositoryPort publisherRepositoryPort;

    @Override
    public Optional<PublisherApplicationResponse> findByDocumentId(String documentId) {
        if (ObjectUtils.isEmpty(documentId)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "documentId");

        return publisherRepositoryPort.findByDocumentId(documentId).map(publisherApplicationMapper::toApplicationResponse);
    }
}
