package com.bezzangss.sign.application.documents.associate.signer.port.out.dto;

import com.bezzangss.sign.application.documents.associate.signer.port.out.dto.request.SignerRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.associate.signer.port.out.dto.response.SignerRepositoryResponse;

import java.util.Optional;

public interface SignerRepositoryPort {
    String create(SignerRepositoryCreateRequest signerRepositoryCreateRequest);

    Optional<SignerRepositoryResponse> findById(String id);
}
