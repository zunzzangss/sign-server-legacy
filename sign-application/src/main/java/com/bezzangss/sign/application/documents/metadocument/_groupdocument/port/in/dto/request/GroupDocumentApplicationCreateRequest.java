package com.bezzangss.sign.application.documents.metadocument._groupdocument.port.in.dto.request;

import com.bezzangss.sign.application.documents.metadocument.port.in.dto.request.BaseDocumentInMetaDocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents.metadocument.port.in.dto.request.DocumentInMetaDocumentApplicationCreateRequest;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GroupDocumentApplicationCreateRequest {
    private final String name;
    private final String description;

    private final BaseDocumentInMetaDocumentApplicationCreateRequest baseDocument;
    private final List<DocumentInMetaDocumentApplicationCreateRequest> documents;
}
