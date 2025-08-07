package com.bezzangss.sign.application.documents.associate.cc.port.in;

import com.bezzangss.sign.application.documents.associate.cc.port.in.dto.request.CcApplicationCreateRequest;

public interface CcCommandApplicationPort {
    String create(CcApplicationCreateRequest ccApplicationCreateRequest);
}
