package com.bezzangss.sign.application.documents.document.application;

import com.bezzangss.sign.application.documents.document.application.mapper.DocumentApplicationMapper;
import com.bezzangss.sign.application.documents.document.port.in.DocumentQueryApplicationPort;
import com.bezzangss.sign.application.documents.document.port.out.DocumentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class DocumentQueryApplication implements DocumentQueryApplicationPort {
    private final DocumentApplicationMapper documentApplicationMapper;
    private final DocumentRepositoryPort documentRepositoryPort;
}
