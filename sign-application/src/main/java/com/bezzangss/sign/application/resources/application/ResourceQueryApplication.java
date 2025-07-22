package com.bezzangss.sign.application.resources.application;

import com.bezzangss.sign.application.resources.port.in.ResourceQueryApplicationPort;
import com.bezzangss.sign.common.resource.ResourceStream;
import com.bezzangss.sign.domain.resource.aggregate.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class ResourceQueryApplication implements ResourceQueryApplicationPort {
    public ResourceStream getResourceStream() {
        Resource resource = Resource.builder()
                .build();

        return resource.toResourceStream(resource1 -> {
            switch (resource1.getType()) {
                case FILE_SYSTEM:
                default:
                    return consumer -> consumer.accept(Files.newInputStream(Paths.get(resource1.getPath())));
            }
        });
    }
}
