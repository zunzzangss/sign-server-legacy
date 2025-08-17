package com.bezzangss.sign.web.internal.documents.metadocument.dto.request;

import com.bezzangss.sign.web.internal.documents.document.dto.request.CcInDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.document.dto.request.PublisherInDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.document.dto.request.SignerInDocumentInternalWebCreateRequest;
import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Setter
@Getter
public class DocumentInMetaDocumentInternalWebCreateRequest {
    private String name;
    private String description;

    private PublisherInDocumentInternalWebCreateRequest publisher;
    private List<SignerInDocumentInternalWebCreateRequest> signers;
    private List<CcInDocumentInternalWebCreateRequest> ccs;
}
