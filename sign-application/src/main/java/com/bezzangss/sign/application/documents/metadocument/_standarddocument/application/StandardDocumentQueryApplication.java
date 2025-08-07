package com.bezzangss.sign.application.documents.metadocument._standarddocument.application;

import com.bezzangss.sign.application.documents.metadocument._standarddocument.application.mapper.StandardDocumentApplicationMapper;
import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.in.StandardDocumentQueryApplicationPort;
import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.out.StandardDocumentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class StandardDocumentQueryApplication implements StandardDocumentQueryApplicationPort {
    private final StandardDocumentApplicationMapper standardDocumentApplicationMapper;
    private final StandardDocumentRepositoryPort standardDocumentRepositoryPort;
}
