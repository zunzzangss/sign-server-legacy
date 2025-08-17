package com.bezzangss.sign.web.internal.documents.metadocument._groupdocument.dto.request;

import com.bezzangss.sign.web.internal.documents.metadocument.dto.request.BaseDocumentInMetaDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument.dto.request.DocumentInMetaDocumentInternalWebCreateRequest;
import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Setter
@Getter
public class GroupDocumentInternalWebCreateRequest {
    private String name;
    private String description;

    private BaseDocumentInMetaDocumentInternalWebCreateRequest baseDocument;
    private List<DocumentInMetaDocumentInternalWebCreateRequest> documents;
}
