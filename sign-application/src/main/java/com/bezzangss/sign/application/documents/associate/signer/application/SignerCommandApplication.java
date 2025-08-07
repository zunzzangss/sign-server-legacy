package com.bezzangss.sign.application.documents.associate.signer.application;

import com.bezzangss.sign.application.documents.associate.signer.application.mapper.SignerApplicationMapper;
import com.bezzangss.sign.application.documents.associate.signer.port.in.SignerApplicationCommandPort;
import com.bezzangss.sign.application.documents.associate.signer.port.in.dto.request.SignerApplicationCreateRequest;
import com.bezzangss.sign.application.documents.associate.signer.port.out.dto.SignerRepositoryPort;
import com.bezzangss.sign.domain.documents.associate.signer.aggregate.Signer;
import com.bezzangss.sign.domain.documents.associate.signer.dto.SignerDomainCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class SignerCommandApplication implements SignerApplicationCommandPort {
    private final SignerApplicationMapper signerApplicationMapper;
    private final SignerRepositoryPort signerRepositoryPort;

    @Override
    public String create(SignerApplicationCreateRequest signerApplicationCreateRequest) {
        // TODO: validation

        Signer signer = Signer.create(
                SignerDomainCreateRequest.builder()
                        .name(signerApplicationCreateRequest.getName())
                        .email(signerApplicationCreateRequest.getEmail())
                        .cellPhone(signerApplicationCreateRequest.getCellPhone())
                        .order(signerApplicationCreateRequest.getOrder())
                        .documentId(signerApplicationCreateRequest.getDocumentId())
                        .build()
        );

        return signerRepositoryPort.create(signerApplicationMapper.toRepositoryCreateRequest(signer));
    }
}
