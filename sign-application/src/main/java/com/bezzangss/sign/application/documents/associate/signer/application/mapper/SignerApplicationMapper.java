package com.bezzangss.sign.application.documents.associate.signer.application.mapper;

import com.bezzangss.sign.application.documents.associate.signer.port.in.dto.response.SignerApplicationResponse;
import com.bezzangss.sign.application.documents.associate.signer.port.out.dto.request.SignerRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.associate.signer.port.out.dto.response.SignerRepositoryResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.domain.documents.associate.signer.aggregate.Signer;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface SignerApplicationMapper extends CommonMapper {
    SignerRepositoryCreateRequest toRepositoryCreateRequest(Signer signer);

    SignerApplicationResponse toApplicationResponse(SignerRepositoryResponse signerRepositoryResponse);
}
