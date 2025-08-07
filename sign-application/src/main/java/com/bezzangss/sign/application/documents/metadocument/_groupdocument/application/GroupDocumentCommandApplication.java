package com.bezzangss.sign.application.documents.metadocument._groupdocument.application;

import com.bezzangss.sign.application.documents.metadocument._groupdocument.application.mapper.GroupDocumentApplicationMapper;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.in.GroupDocumentCommandApplicationPort;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.out.GroupDocumentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class GroupDocumentCommandApplication implements GroupDocumentCommandApplicationPort {
    private final GroupDocumentApplicationMapper groupDocumentApplicationMapper;
    private final GroupDocumentRepositoryPort groupDocumentRepositoryPort;
}
