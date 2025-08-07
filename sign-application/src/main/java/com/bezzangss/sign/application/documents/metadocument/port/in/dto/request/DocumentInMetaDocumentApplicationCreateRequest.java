package com.bezzangss.sign.application.documents.metadocument.port.in.dto.request;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.documents.document.port.in.dto.request.common.CcInDocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents.document.port.in.dto.request.common.PublisherInDocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents.document.port.in.dto.request.common.SignerInDocumentApplicationCreateRequest;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@Getter
public class DocumentInMetaDocumentApplicationCreateRequest {
    private final String name;
    private final String description;

    private final PublisherInDocumentApplicationCreateRequest publisher;
    private final List<SignerInDocumentApplicationCreateRequest> signers;
    private final List<CcInDocumentApplicationCreateRequest> ccs;

    @Builder
    public DocumentInMetaDocumentApplicationCreateRequest(String name, String description, PublisherInDocumentApplicationCreateRequest publisher, List<SignerInDocumentApplicationCreateRequest> signers, List<CcInDocumentApplicationCreateRequest> ccs) {
        if (ObjectUtils.isEmpty(name)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "name");
        if (ObjectUtils.isEmpty(publisher)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "publisher");
        if (ObjectUtils.isEmpty(signers)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "signers");

        this.name = name;
        this.description = description;
        this.publisher = publisher;
        this.signers = signers;
        this.ccs = ccs;
    }
}
