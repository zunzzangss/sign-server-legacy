package com.bezzangss.sign.web.internal.documents.document.dto.request;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Setter
@Getter
public class CcInDocumentInternalWebCreateRequest {
    private String name;
    private String email;
    private String cellPhone;
}
