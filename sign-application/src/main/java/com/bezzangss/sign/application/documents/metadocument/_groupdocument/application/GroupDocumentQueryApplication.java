package com.bezzangss.sign.application.documents.metadocument._groupdocument.application;

import com.bezzangss.sign.application.documents.metadocument._groupdocument.application.mapper.GroupDocumentApplicationMapper;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.in.GroupDocumentQueryApplicationPort;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.out.GroupDocumentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class GroupDocumentQueryApplication implements GroupDocumentQueryApplicationPort {
    private final GroupDocumentApplicationMapper groupDocumentApplicationMapper;
    private final GroupDocumentRepositoryPort groupDocumentRepositoryPort;
}
