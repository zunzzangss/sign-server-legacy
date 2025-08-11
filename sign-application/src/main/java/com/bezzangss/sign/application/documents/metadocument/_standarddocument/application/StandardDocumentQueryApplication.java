package com.bezzangss.sign.application.documents.metadocument._standarddocument.application;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.documents.metadocument._standarddocument.application.mapper.StandardDocumentApplicationMapper;
import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.in.StandardDocumentQueryApplicationPort;
import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.in.dto.response.StandardDocumentApplicationResponse;
import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.out.StandardDocumentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class StandardDocumentQueryApplication implements StandardDocumentQueryApplicationPort {
    private final StandardDocumentApplicationMapper standardDocumentApplicationMapper;
    private final StandardDocumentRepositoryPort standardDocumentRepositoryPort;

    @Override
    public Optional<StandardDocumentApplicationResponse> findById(String id) {
        if (ObjectUtils.isEmpty(id)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");

        return standardDocumentRepositoryPort.findById(id).map(standardDocumentApplicationMapper::toApplicationResponse);
    }
}
