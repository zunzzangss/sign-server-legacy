package com.bezzangss.sign.application.resources.resourcereference.port.in;

import com.bezzangss.sign.application.resources.resourcereference.port.in.dto.request.ResourceReferenceApplicationCreateRequest;

public interface ResourceReferenceCommandApplicationPort {
    String create(ResourceReferenceApplicationCreateRequest resourceReferenceApplicationCreateRequest);
}
