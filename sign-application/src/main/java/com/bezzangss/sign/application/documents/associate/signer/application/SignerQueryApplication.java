package com.bezzangss.sign.application.documents.associate.signer.application;

import com.bezzangss.sign.application.documents.associate.signer.application.mapper.SignerApplicationMapper;
import com.bezzangss.sign.application.documents.associate.signer.port.in.SignerApplicationQueryPort;
import com.bezzangss.sign.application.documents.associate.signer.port.out.dto.SignerRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class SignerQueryApplication implements SignerApplicationQueryPort {
    private final SignerApplicationMapper signerApplicationMapper;
    private final SignerRepositoryPort signerRepositoryPort;
}
