package com.bezzangss.sign.application.resources.port.in;

import com.bezzangss.sign.application.resources.port.in.dto.request.ResourceApplicationCreateRequest;

public interface ResourceCommandApplicationPort {
    String create(ResourceApplicationCreateRequest resourceApplicationCreateRequest);
}
