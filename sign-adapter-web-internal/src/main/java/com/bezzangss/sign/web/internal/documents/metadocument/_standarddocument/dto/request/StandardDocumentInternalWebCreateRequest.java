package com.bezzangss.sign.web.internal.documents.metadocument._standarddocument.dto.request;

import com.bezzangss.sign.web.internal.documents.metadocument.dto.request.BaseDocumentInMetaDocumentWebInternalCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument.dto.request.DocumentInMetaDocumentWebInternalCreateRequest;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Setter
@Getter
public class StandardDocumentInternalWebCreateRequest {
    private String name;
    private String description;

    private BaseDocumentInMetaDocumentWebInternalCreateRequest baseDocument;
    private DocumentInMetaDocumentWebInternalCreateRequest document;
}
