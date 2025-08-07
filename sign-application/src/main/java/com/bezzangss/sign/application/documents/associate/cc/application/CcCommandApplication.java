package com.bezzangss.sign.application.documents.associate.cc.application;

import com.bezzangss.sign.application.documents.associate.cc.application.mapper.CcApplicationMapper;
import com.bezzangss.sign.application.documents.associate.cc.port.in.CcCommandApplicationPort;
import com.bezzangss.sign.application.documents.associate.cc.port.in.dto.request.CcApplicationCreateRequest;
import com.bezzangss.sign.application.documents.associate.cc.port.out.CcRepositoryPort;
import com.bezzangss.sign.domain.documents.associate.cc.aggregate.Cc;
import com.bezzangss.sign.domain.documents.associate.cc.dto.CcDomainCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class CcCommandApplication implements CcCommandApplicationPort {
    private final CcApplicationMapper ccApplicationMapper;
    private final CcRepositoryPort ccRepositoryPort;

    @Override
    public String create(CcApplicationCreateRequest ccApplicationCreateRequest) {
        // TODO: validation

        Cc cc = Cc.create(
                CcDomainCreateRequest.builder()
                        .name(ccApplicationCreateRequest.getName())
                        .email(ccApplicationCreateRequest.getEmail())
                        .cellPhone(ccApplicationCreateRequest.getCellPhone())
                        .documentId(ccApplicationCreateRequest.getDocumentId())
                        .build()
        );

        return ccRepositoryPort.create(ccApplicationMapper.toRepositoryCreateRequest(cc));
    }
}
