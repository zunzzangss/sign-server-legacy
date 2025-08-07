package com.bezzangss.sign.repository.jpa.documents.associate.signer.mapper;

import com.bezzangss.sign.application.documents.associate.signer.port.out.dto.request.SignerRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.associate.signer.port.out.dto.response.SignerRepositoryResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.repository.jpa.documents.associate.signer.entity.SignerJpaEntity;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface SignerJpaMapper extends CommonMapper {
    SignerJpaEntity toEntity(SignerRepositoryCreateRequest signerRepositoryCreateRequest);

    SignerRepositoryResponse toResponse(SignerJpaEntity signerJpaEntity);
}
