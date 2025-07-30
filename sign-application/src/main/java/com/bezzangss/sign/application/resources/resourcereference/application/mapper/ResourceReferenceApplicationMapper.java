package com.bezzangss.sign.application.resources.resourcereference.application.mapper;

import com.bezzangss.sign.application.resources.resourcereference.port.in.dto.response.ResourceReferenceApplicationResponse;
import com.bezzangss.sign.application.resources.resourcereference.port.out.dto.request.ResourceReferenceRepositoryCreateRequest;
import com.bezzangss.sign.application.resources.resourcereference.port.out.dto.response.ResourceReferenceRepositoryResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.domain.resources.resourcereference.aggregate.ResourceReference;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface ResourceReferenceApplicationMapper extends CommonMapper {
    ResourceReferenceRepositoryCreateRequest toRepositoryCreateRequest(ResourceReference resourceReference);

    ResourceReferenceApplicationResponse toApplicationResponse(ResourceReferenceRepositoryResponse resourceReferenceRepositoryResponse);
}
