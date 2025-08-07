package com.bezzangss.sign.repository.jpa.documents.associate.signer;

import com.bezzangss.sign.application.documents.associate.signer.port.out.dto.SignerRepositoryPort;
import com.bezzangss.sign.application.documents.associate.signer.port.out.dto.request.SignerRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.associate.signer.port.out.dto.response.SignerRepositoryResponse;
import com.bezzangss.sign.repository.jpa.documents.associate.signer.mapper.SignerJpaMapper;
import com.bezzangss.sign.repository.jpa.documents.associate.signer.repository.SignerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
    public Optional<SignerRepositoryResponse> findById(String id) {
        return signerJpaRepository.findById(id).map(signerJpaMapper::toResponse);
    }
}
