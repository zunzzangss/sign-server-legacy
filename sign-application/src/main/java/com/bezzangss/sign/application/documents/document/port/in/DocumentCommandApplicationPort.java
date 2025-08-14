package com.bezzangss.sign.application.documents.document.port.in;

import com.bezzangss.sign.application.documents.document.port.in.dto.request.DocumentApplicationCreateRequest;

public interface DocumentCommandApplicationPort {
    String create(DocumentApplicationCreateRequest documentApplicationCreateRequest);

    void process(String id);
}
