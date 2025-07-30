package com.bezzangss.sign.application.resources.resource.port.in;

import com.bezzangss.sign.application.resources.resource.port.in.dto.response.ResourceApplicationResponse;
import com.bezzangss.sign.common.inputstream.InputStreamHandler;

import java.util.Optional;

public interface ResourceQueryApplicationPort {
    Optional<ResourceApplicationResponse> findById(String id);

    InputStreamHandler readById(String id);
}
