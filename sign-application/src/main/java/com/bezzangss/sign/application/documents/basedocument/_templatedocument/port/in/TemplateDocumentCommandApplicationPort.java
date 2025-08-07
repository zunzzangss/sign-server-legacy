package com.bezzangss.sign.application.documents.basedocument._templatedocument.port.in;

import com.bezzangss.sign.application.documents.basedocument._templatedocument.port.in.dto.request.TemplateDocumentApplicationCreateRequest;

public interface TemplateDocumentCommandApplicationPort {
    String create(TemplateDocumentApplicationCreateRequest templateDocumentApplicationCreateRequest);
}
