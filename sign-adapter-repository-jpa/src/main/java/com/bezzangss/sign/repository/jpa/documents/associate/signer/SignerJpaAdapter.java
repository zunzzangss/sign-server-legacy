package com.bezzangss.sign.repository.jpa.documents.associate.signer;

import com.bezzangss.sign.application.documents.associate.signer.port.out.dto.SignerRepositoryPort;
import com.bezzangss.sign.application.documents.associate.signer.port.out.dto.request.SignerRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.associate.signer.port.out.dto.request.SignerRepositoryUpdateRequest;
import com.bezzangss.sign.application.documents.associate.signer.port.out.dto.response.SignerRepositoryResponse;
import com.bezzangss.sign.repository.jpa.JpaRepositoryException;
import com.bezzangss.sign.repository.jpa.documents.associate.signer.entity.SignerJpaEntity;
import com.bezzangss.sign.repository.jpa.documents.associate.signer.mapper.SignerJpaMapper;
import com.bezzangss.sign.repository.jpa.documents.associate.signer.repository.SignerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.bezzangss.sign.common.exception.ErrorCode.SIGNER_NOT_FOUND_EXCEPTION;

@RequiredArgsConstructor
@Repository
public class SignerJpaAdapter implements SignerRepositoryPort {
    private final SignerJpaMapper signerJpaMapper;
    private final SignerJpaRepository signerJpaRepository;

    @Override
    public String create(SignerRepositoryCreateRequest signerRepositoryCreateRequest) {
        return signerJpaRepository.save(signerJpaMapper.toEntity(signerRepositoryCreateRequest)).getId();
    }

    @Override
    public String update(SignerRepositoryUpdateRequest signerRepositoryUpdateRequest) {
        SignerJpaEntity signerJpaEntity = signerJpaRepository.findById(signerRepositoryUpdateRequest.getId()).orElseThrow(() -> new JpaRepositoryException(SIGNER_NOT_FOUND_EXCEPTION, signerRepositoryUpdateRequest.getId()));
        signerJpaMapper.update(signerRepositoryUpdateRequest, signerJpaEntity);

        return signerJpaRepository.save(signerJpaEntity).getId();
    }

    @Override
    public List<String> updateAll(List<SignerRepositoryUpdateRequest> signerRepositoryUpdateRequests) {
        List<SignerJpaEntity> signerJpaEntities = signerRepositoryUpdateRequests.stream()
                .map(signerRepositoryUpdateRequest -> {
                    SignerJpaEntity signerJpaEntity = signerJpaRepository.findById(signerRepositoryUpdateRequest.getId()).orElseThrow(() -> new JpaRepositoryException(SIGNER_NOT_FOUND_EXCEPTION, signerRepositoryUpdateRequest.getId()));
                    signerJpaMapper.update(signerRepositoryUpdateRequest, signerJpaEntity);

                    return signerJpaEntity;
                })
                .collect(Collectors.toList());

        return signerJpaRepository.save(signerJpaEntities).stream()
                .map(SignerJpaEntity::getId)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SignerRepositoryResponse> findById(String id) {
        return signerJpaRepository.findById(id).map(signerJpaMapper::toResponse);
    }

    @Override
    public List<SignerRepositoryResponse> findAllByDocumentId(String documentId) {
        return signerJpaRepository.findAllByDocumentId(documentId).stream()
                .map(signerJpaMapper::toResponse)
                .collect(Collectors.toList());
    }
}
