package com.bezzangss.sign.application.documents.associate.signer.port.out.dto;

import com.bezzangss.sign.application.documents.associate.signer.port.out.dto.request.SignerRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.associate.signer.port.out.dto.request.SignerRepositoryUpdateRequest;
import com.bezzangss.sign.application.documents.associate.signer.port.out.dto.response.SignerRepositoryResponse;

import java.util.List;
import java.util.Optional;

public interface SignerRepositoryPort {
    String create(SignerRepositoryCreateRequest signerRepositoryCreateRequest);

    String update(SignerRepositoryUpdateRequest signerRepositoryUpdateRequest);

    List<String> updateAll(List<SignerRepositoryUpdateRequest> signerRepositoryUpdateRequests);

    Optional<SignerRepositoryResponse> findById(String id);

    List<SignerRepositoryResponse> findAllByDocumentId(String documentId);
}
