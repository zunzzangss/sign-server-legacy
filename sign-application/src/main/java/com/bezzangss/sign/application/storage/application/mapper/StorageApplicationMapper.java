package com.bezzangss.sign.application.storage.application.mapper;

import com.bezzangss.sign.application.storage.application.bridge.dto.response.StorageApplicationWriteResponse;
import com.bezzangss.sign.application.storage.port.out.dto.response.StorageWriteResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface StorageApplicationMapper extends CommonMapper {
    StorageApplicationWriteResponse toWriteResponse(StorageWriteResponse storageWriteResponse);
}
