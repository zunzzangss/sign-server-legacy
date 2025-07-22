package com.bezzangss.sign.application.resources.application;

import com.bezzangss.sign.application.resources.port.in.ResourceCommandApplicationPort;
import com.bezzangss.sign.application.resources.port.in.dto.request.ResourceApplicationCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class ResourceCommandApplication implements ResourceCommandApplicationPort {

    @Override
    public String create(ResourceApplicationCreateRequest resourceApplicationCreateRequest) {
        return "";
    }
}
