package com.bezzangss.sign.application.documents.associate.cc.application;

import com.bezzangss.sign.application.documents.associate.cc.application.mapper.CcApplicationMapper;
import com.bezzangss.sign.application.documents.associate.cc.port.in.CcQueryApplicationPort;
import com.bezzangss.sign.application.documents.associate.cc.port.out.CcRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class CcQueryApplication implements CcQueryApplicationPort {
    private final CcApplicationMapper ccApplicationMapper;
    private final CcRepositoryPort ccRepositoryPort;
}
