package com.bezzangss.sign.application.resources.port.in;

import com.bezzangss.sign.common.inputstream.InputStreamHandler;

public interface ResourceQueryApplicationPort {
    InputStreamHandler readById(String id);
}
