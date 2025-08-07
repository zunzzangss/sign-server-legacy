package com.bezzangss.sign.repository.jpa.documents.associate.cc;

import com.bezzangss.sign.application.documents.associate.cc.port.out.CcRepositoryPort;
import com.bezzangss.sign.application.documents.associate.cc.port.out.dto.request.CcRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.associate.cc.port.out.dto.response.CcRepositoryResponse;
import com.bezzangss.sign.repository.jpa.documents.associate.cc.mapper.CcJpaMapper;
import com.bezzangss.sign.repository.jpa.documents.associate.cc.repository.CcJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CcJpaAdapter implements CcRepositoryPort {
    private final CcJpaMapper ccJpaMapper;
    private final CcJpaRepository ccJpaRepository;

    @Override
    public String create(CcRepositoryCreateRequest ccRepositoryCreateRequest) {
        return ccJpaRepository.save(ccJpaMapper.toEntity(ccRepositoryCreateRequest)).getId();
    }

    @Override
    public Optional<CcRepositoryResponse> findById(String id) {
        return ccJpaRepository.findById(id).map(ccJpaMapper::toResponse);
    }
}
