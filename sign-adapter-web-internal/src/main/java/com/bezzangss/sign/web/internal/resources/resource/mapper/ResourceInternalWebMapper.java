package com.bezzangss.sign.web.internal.resources.resource.mapper;

import com.bezzangss.sign.application.resources.resource.port.in.dto.response.ResourceApplicationResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.web.internal.resources.resource.dto.response.ResourceInternalWebResponse;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface ResourceInternalWebMapper extends CommonMapper {
    ResourceInternalWebResponse toResponse(ResourceApplicationResponse resourceApplicationResponse);
}
