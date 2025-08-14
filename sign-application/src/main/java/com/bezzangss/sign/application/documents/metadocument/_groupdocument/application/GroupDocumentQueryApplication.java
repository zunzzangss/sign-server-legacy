package com.bezzangss.sign.application.documents.metadocument._groupdocument.application;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.application.mapper.GroupDocumentApplicationMapper;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.in.GroupDocumentQueryApplicationPort;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.in.dto.response.GroupDocumentApplicationResponse;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.out.GroupDocumentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class GroupDocumentQueryApplication implements GroupDocumentQueryApplicationPort {
    private final GroupDocumentApplicationMapper groupDocumentApplicationMapper;
    private final GroupDocumentRepositoryPort groupDocumentRepositoryPort;

    @Override
    public Optional<GroupDocumentApplicationResponse> findById(String id) {
        if (ObjectUtils.isEmpty(id)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");

        return groupDocumentRepositoryPort.findById(id).map(groupDocumentApplicationMapper::toApplicationResponse);
    }
}
