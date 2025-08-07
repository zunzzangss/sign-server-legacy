package com.bezzangss.sign.repository.jpa.documents.associate.cc.mapper;

import com.bezzangss.sign.application.documents.associate.cc.port.out.dto.request.CcRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.associate.cc.port.out.dto.response.CcRepositoryResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.repository.jpa.documents.associate.cc.entity.CcJpaEntity;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface CcJpaMapper extends CommonMapper {
    CcJpaEntity toEntity(CcRepositoryCreateRequest ccRepositoryCreateRequest);

    CcRepositoryResponse toResponse(CcJpaEntity ccJpaEntity);
}
