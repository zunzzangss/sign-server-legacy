package com.bezzangss.sign.application.resources.application.mapper;

import com.bezzangss.sign.application.resources.port.out.dto.request.ResourceRepositoryCreateRequest;
import com.bezzangss.sign.application.resources.port.out.dto.response.ResourceRepositoryResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.domain.resources.resource.aggregate.Resource;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface ResourceApplicationMapper extends CommonMapper {
    Resource toDomain(ResourceRepositoryResponse resourceRepositoryResponse);

    ResourceRepositoryCreateRequest toRepositoryCreateRequest(Resource resource);
}
