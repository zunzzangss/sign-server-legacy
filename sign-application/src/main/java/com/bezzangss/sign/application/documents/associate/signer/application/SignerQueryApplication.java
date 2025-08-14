package com.bezzangss.sign.application.documents.associate.signer.application;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.documents.associate.signer.application.bridge.SignerQueryApplicationBridge;
import com.bezzangss.sign.application.documents.associate.signer.application.mapper.SignerApplicationMapper;
import com.bezzangss.sign.application.documents.associate.signer.port.in.SignerApplicationQueryPort;
import com.bezzangss.sign.application.documents.associate.signer.port.out.dto.SignerRepositoryPort;
import com.bezzangss.sign.domain.documents.associate.signer.aggregate.Signer;
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
public class SignerQueryApplication implements SignerApplicationQueryPort, SignerQueryApplicationBridge {
    private final SignerApplicationMapper signerApplicationMapper;
    private final SignerRepositoryPort signerRepositoryPort;

    @Override
    public List<Signer> findAllDomainByDocumentId(String documentId) {
        if (ObjectUtils.isEmpty(documentId)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "documentId");

        return signerRepositoryPort.findAllByDocumentId(documentId).stream()
                .map(signerApplicationMapper::toDomain)
                .collect(Collectors.toList());
    }
}
