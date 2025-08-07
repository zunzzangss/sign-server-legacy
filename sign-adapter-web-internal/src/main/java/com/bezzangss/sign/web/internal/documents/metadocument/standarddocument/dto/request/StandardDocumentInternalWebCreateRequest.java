package com.bezzangss.sign.web.internal.documents.metadocument.standarddocument.dto.request;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Setter
@Getter
public class StandardDocumentInternalWebCreateRequest {
    private String name;
    private String description;

//    private BaseDocument baseDocument;
//    private Document document;
}
