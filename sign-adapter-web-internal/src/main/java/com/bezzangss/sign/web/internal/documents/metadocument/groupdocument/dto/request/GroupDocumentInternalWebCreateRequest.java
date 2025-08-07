package com.bezzangss.sign.web.internal.documents.metadocument.groupdocument.dto.request;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Setter
@Getter
public class GroupDocumentInternalWebCreateRequest {
    private String name;
    private String description;

//    private BaseDocument basedocument;
//    private List<Document> document;
}
