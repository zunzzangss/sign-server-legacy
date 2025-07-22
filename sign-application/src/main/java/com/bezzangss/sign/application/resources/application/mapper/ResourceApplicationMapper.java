package com.bezzangss.sign.application.resources.application.mapper;

import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface ResourceApplicationMapper extends CommonMapper {
}
