package com.bezzangss.sign.application.resources.port.in;

import com.bezzangss.sign.common.resource.ResourceStream;

public interface ResourceQueryApplicationPort {
    ResourceStream readById(String id);
}
