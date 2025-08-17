package com.bezzangss.sign.web.internal.documents.associate.signer.mapper;

import com.bezzangss.sign.application.documents.associate.signer.port.in.dto.response.SignerApplicationResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.web.internal.documents.associate.signer.dto.response.SignerInternalWebResponse;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface SignerInternalWebMapper extends CommonMapper {
    SignerInternalWebResponse toResponse(SignerApplicationResponse signerApplicationResponse);
}
