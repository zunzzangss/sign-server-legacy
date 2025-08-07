package com.bezzangss.sign.application.documents.associate.cc.application.mapper;

import com.bezzangss.sign.application.documents.associate.cc.port.in.dto.response.CcApplicationResponse;
import com.bezzangss.sign.application.documents.associate.cc.port.out.dto.request.CcRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.associate.cc.port.out.dto.response.CcRepositoryResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.domain.documents.associate.cc.aggregate.Cc;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface CcApplicationMapper extends CommonMapper {
    CcRepositoryCreateRequest toRepositoryCreateRequest(Cc cc);

    CcApplicationResponse toApplicationResponse(CcRepositoryResponse ccRepositoryResponse);
}
