package com.bezzangss.sign.application.documents.metadocument.application;

import com.bezzangss.sign.application.documents.metadocument.port.in.MetaDocumentQueryApplicationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class MetaDocumentQueryApplication implements MetaDocumentQueryApplicationPort {
}
