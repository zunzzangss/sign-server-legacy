package com.bezzangss.sign.web.internal.documents.metadocument.dto.request;

import com.bezzangss.sign.web.internal.documents.document.dto.request.CcInDocumentWebInternalCreateRequest;
import com.bezzangss.sign.web.internal.documents.document.dto.request.PublisherInDocumentWebInternalCreateRequest;
import com.bezzangss.sign.web.internal.documents.document.dto.request.SignerInDocumentWebInternalCreateRequest;
import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Setter
@Getter
public class DocumentInMetaDocumentWebInternalCreateRequest {
    private String name;
    private String description;

    private PublisherInDocumentWebInternalCreateRequest publisher;
    private List<SignerInDocumentWebInternalCreateRequest> signers;
    private List<CcInDocumentWebInternalCreateRequest> ccs;
}
