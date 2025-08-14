package com.bezzangss.sign.web.internal.documents.metadocument._groupdocument.dto.request;

import com.bezzangss.sign.web.internal.documents.metadocument.dto.request.BaseDocumentInMetaDocumentWebInternalCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument.dto.request.DocumentInMetaDocumentWebInternalCreateRequest;
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

    private BaseDocumentInMetaDocumentWebInternalCreateRequest baseDocument;
    private List<DocumentInMetaDocumentWebInternalCreateRequest> documents;
}
