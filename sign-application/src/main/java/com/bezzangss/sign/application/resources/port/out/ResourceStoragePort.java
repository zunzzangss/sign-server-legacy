package com.bezzangss.sign.application.resources.port.out;

import com.bezzangss.sign.application.resources.port.out.dto.request.ResourceStorageWriteRequest;
import com.bezzangss.sign.application.resources.port.out.dto.response.ResourceStorageWriteResponse;
import com.bezzangss.sign.common.resource.ResourceStream;

public interface ResourceStoragePort {
    String resourceType();

    ResourceStream read(String source);

    ResourceStorageWriteResponse write(ResourceStorageWriteRequest resourceStorageWriteRequest);
}
