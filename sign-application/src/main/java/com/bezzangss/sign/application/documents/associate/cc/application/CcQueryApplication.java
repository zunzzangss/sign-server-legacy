package com.bezzangss.sign.application.documents.associate.cc.application;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.documents.associate.cc.application.mapper.CcApplicationMapper;
import com.bezzangss.sign.application.documents.associate.cc.port.in.CcQueryApplicationPort;
import com.bezzangss.sign.application.documents.associate.cc.port.in.dto.response.CcApplicationResponse;
import com.bezzangss.sign.application.documents.associate.cc.port.out.CcRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class CcQueryApplication implements CcQueryApplicationPort {
    private final CcApplicationMapper ccApplicationMapper;
    private final CcRepositoryPort ccRepositoryPort;

    @Override
    public List<CcApplicationResponse> findAllByDocumentId(String documentId) {
        if (ObjectUtils.isEmpty(documentId)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "documentId");

        return ccRepositoryPort.findAllByDocumentId(documentId).stream()
                .map(ccApplicationMapper::toApplicationResponse)
                .collect(Collectors.toList());
    }
}
