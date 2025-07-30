package com.bezzangss.sign.application.resources.resource.application;

import com.bezzangss.sign.application.resources.resource.application.mapper.ResourceApplicationMapper;
import com.bezzangss.sign.application.resources.resource.port.in.ResourceCommandApplicationPort;
import com.bezzangss.sign.application.resources.resource.port.in.dto.request.ResourceApplicationCreateRequest;
import com.bezzangss.sign.application.resources.resource.port.out.ResourceRepositoryPort;
import com.bezzangss.sign.application.storage.application.bridge.StorageCommandApplicationBridge;
import com.bezzangss.sign.application.storage.application.bridge.dto.request.StorageApplicationWriteRequest;
import com.bezzangss.sign.application.storage.application.bridge.dto.response.StorageApplicationWriteResponse;
import com.bezzangss.sign.common.enums.EnumConverter;
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
                        .typeProvider(EnumConverter.from(ResourceType.class, type))
                        .inputStreamHandler(resourceApplicationCreateRequest.getInputStreamHandler())
                        .build()
        );

        Resource resource = Resource.create(
                ResourceCreateRequest.builder()
                        .type(EnumConverter.from(ResourceType.class, type))
                        .source(storageApplicationWriteResponse.getSource())
                        .size(storageApplicationWriteResponse.getSize())
                        .build()
        );

        return resourceRepositoryPort.create(resourceApplicationMapper.toRepositoryCreateRequest(resource));
    }
}
