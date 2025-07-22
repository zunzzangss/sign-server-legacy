package com.bezzangss.sign.repositoryjpa.resources.mapper;

import com.bezzangss.sign.application.resources.port.out.dto.request.ResourceRepositoryCreateRequest;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.repositoryjpa.resources.entity.ResourceJpaEntity;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface ResourceJpaMapper extends CommonMapper {
    ResourceJpaEntity toEntity(ResourceRepositoryCreateRequest resourceRepositoryCreateRequest);
}
