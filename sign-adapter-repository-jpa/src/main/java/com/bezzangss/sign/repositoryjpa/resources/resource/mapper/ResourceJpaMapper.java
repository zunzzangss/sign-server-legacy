package com.bezzangss.sign.repositoryjpa.resources.resource.mapper;

import com.bezzangss.sign.application.resources.resource.port.out.dto.request.ResourceRepositoryCreateRequest;
import com.bezzangss.sign.application.resources.resource.port.out.dto.response.ResourceRepositoryResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.repositoryjpa.resources.resource.entity.ResourceJpaEntity;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface ResourceJpaMapper extends CommonMapper {
    ResourceJpaEntity toEntity(ResourceRepositoryCreateRequest resourceRepositoryCreateRequest);

    ResourceRepositoryResponse toResponse(ResourceJpaEntity resourceJpaEntity);
}
