package com.bezzangss.sign.web.internal.documents.metadocument.dto.request;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Setter
@Getter
public class BaseDocumentInMetaDocumentInternalWebCreateRequest {
    private String id;
    private String type;
}
