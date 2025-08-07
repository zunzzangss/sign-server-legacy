package com.bezzangss.sign.application.documents.associate.cc.port.out;

import com.bezzangss.sign.application.documents.associate.cc.port.out.dto.request.CcRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.associate.cc.port.out.dto.response.CcRepositoryResponse;

import java.util.Optional;

public interface CcRepositoryPort {
    String create(CcRepositoryCreateRequest ccRepositoryCreateRequest);

    Optional<CcRepositoryResponse> findById(String id);
}
