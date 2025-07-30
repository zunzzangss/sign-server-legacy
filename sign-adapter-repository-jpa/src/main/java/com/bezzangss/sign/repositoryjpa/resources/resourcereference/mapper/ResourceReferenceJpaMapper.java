package com.bezzangss.sign.repositoryjpa.resources.resourcereference.mapper;

import com.bezzangss.sign.application.resources.resourcereference.port.out.dto.request.ResourceReferenceRepositoryCreateRequest;
import com.bezzangss.sign.application.resources.resourcereference.port.out.dto.response.ResourceReferenceRepositoryResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.repositoryjpa.resources.resourcereference.entity.ResourceReferenceJpaEntity;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface ResourceReferenceJpaMapper extends CommonMapper {
    ResourceReferenceJpaEntity toEntity(ResourceReferenceRepositoryCreateRequest resourceReferenceRepositoryCreateRequest);

    ResourceReferenceRepositoryResponse toResponse(ResourceReferenceJpaEntity resourceReferenceJpaEntity);
}
