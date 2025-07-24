package com.bezzangss.sign.application.resources.application;

import com.bezzangss.sign.application.resources.application.mapper.ResourceApplicationMapper;
import com.bezzangss.sign.application.resources.port.in.ResourceCommandApplicationPort;
import com.bezzangss.sign.application.resources.port.in.dto.request.ResourceApplicationCreateRequest;
import com.bezzangss.sign.application.resources.port.out.ResourceRepositoryPort;
import com.bezzangss.sign.application.storage.application.bridge.StorageCommandApplicationBridge;
import com.bezzangss.sign.application.storage.application.bridge.dto.request.StorageApplicationWriteRequest;
import com.bezzangss.sign.application.storage.application.bridge.dto.response.StorageApplicationWriteResponse;
import com.bezzangss.sign.domain.resources.resource.ResourceType;
import com.bezzangss.sign.domain.resources.resource.aggregate.Resource;
import com.bezzangss.sign.domain.resources.resource.dto.ResourceCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class ResourceCommandApplication implements ResourceCommandApplicationPort {
    private final ResourceApplicationMapper resourceApplicationMapper;
    private final ResourceRepositoryPort resourceRepositoryPort;

    private final StorageCommandApplicationBridge storageCommandApplicationBridge;

    @Override
    public String create(ResourceApplicationCreateRequest resourceApplicationCreateRequest) {
        String type = resourceApplicationCreateRequest.getType();

        StorageApplicationWriteResponse storageApplicationWriteResponse = storageCommandApplicationBridge.write(
                StorageApplicationWriteRequest.builder()
                        .typeProvider(ResourceType.from(type))
                        .inputStreamHandler(resourceApplicationCreateRequest.getInputStreamHandler())
                        .build()
        );

        Resource resource = Resource.create(
                ResourceCreateRequest.builder()
                        .type(ResourceType.from(type))
                        .source(storageApplicationWriteResponse.getSource())
                        .size(storageApplicationWriteResponse.getSize())
                        .build()
        );

        return resourceRepositoryPort.create(resourceApplicationMapper.toRepositoryCreateRequest(resource));
    }
}
