package com.bezzangss.sign.application.resources.resource.port.in;

import com.bezzangss.sign.application.resources.resource.port.in.dto.request.ResourceApplicationCreateRequest;

public interface ResourceCommandApplicationPort {
    String create(ResourceApplicationCreateRequest resourceApplicationCreateRequest);
}
