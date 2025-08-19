package com.bezzangss.sign.application.documents.associate.cc.port.in;

import com.bezzangss.sign.application.documents.associate.cc.port.in.dto.response.CcApplicationResponse;

import java.util.List;

public interface CcQueryApplicationPort {
    List<CcApplicationResponse> findAllByDocumentId(String documentId);
}
