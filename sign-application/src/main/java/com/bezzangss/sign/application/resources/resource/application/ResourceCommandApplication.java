package com.bezzangss.sign.application.resources.resource.application;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.resources.resource.application.mapper.ResourceApplicationMapper;
import com.bezzangss.sign.application.resources.resource.port.in.ResourceCommandApplicationPort;
import com.bezzangss.sign.application.resources.resource.port.in.dto.request.ResourceApplicationCreateRequest;
import com.bezzangss.sign.application.resources.resource.port.out.ResourceRepositoryPort;
import com.bezzangss.sign.application.storage.application.bridge.StorageCommandApplicationBridge;
import com.bezzangss.sign.application.storage.application.bridge.StorageQueryApplicationBridge;
import com.bezzangss.sign.application.storage.application.bridge.dto.request.StorageApplicationReadRequest;
import com.bezzangss.sign.application.storage.application.bridge.dto.request.StorageApplicationWriteRequest;
import com.bezzangss.sign.application.storage.application.bridge.dto.response.StorageApplicationWriteResponse;
import com.bezzangss.sign.common.enums.EnumConverter;
import com.bezzangss.sign.common.inputstream.InputStreamHandler;
import com.bezzangss.sign.domain.resources.resource.ResourceType;
import com.bezzangss.sign.domain.resources.resource.aggregate.Resource;
import com.bezzangss.sign.domain.resources.resource.dto.ResourceCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;
import static com.bezzangss.sign.common.exception.ErrorCode.RESOURCE_NOT_FOUND_EXCEPTION;

@RequiredArgsConstructor
@Transactional
@Component
public class ResourceCommandApplication implements ResourceCommandApplicationPort {
    private final ResourceApplicationMapper resourceApplicationMapper;
    private final ResourceRepositoryPort resourceRepositoryPort;

    private final StorageCommandApplicationBridge storageCommandApplicationBridge;
    private final StorageQueryApplicationBridge storageQueryApplicationBridge;

    @Override
    public String create(ResourceApplicationCreateRequest resourceApplicationCreateRequest) {
        ResourceType type = EnumConverter.from(ResourceType.class, resourceApplicationCreateRequest.getType());
        InputStreamHandler inputStreamHandler = resourceApplicationCreateRequest.getInputStreamHandler();

        return this.create(type, inputStreamHandler);
    }

    @Override
    public String replicate(String id) {
        if (ObjectUtils.isEmpty(id)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");

        Resource original = resourceRepositoryPort.findById(id).map(resourceApplicationMapper::toDomain).orElseThrow(() -> new ApplicationException(RESOURCE_NOT_FOUND_EXCEPTION, id));

        ResourceType type = original.getType();
        InputStreamHandler inputStreamHandler = storageQueryApplicationBridge.read(
                StorageApplicationReadRequest.builder()
                        .typeProvider(type)
                        .source(original.getSource())
                        .build()
        );

        return this.create(type, inputStreamHandler);
    }

    private String create(ResourceType type, InputStreamHandler inputStreamHandler) {
        StorageApplicationWriteResponse storageApplicationWriteResponse = storageCommandApplicationBridge.write(
                StorageApplicationWriteRequest.builder()
                        .typeProvider(type)
                        .inputStreamHandler(inputStreamHandler)
                        .build()
        );

        Resource resource = Resource.create(
                ResourceCreateRequest.builder()
                        .type(type)
                        .source(storageApplicationWriteResponse.getSource())
                        .size(storageApplicationWriteResponse.getSize())
                        .build()
        );

        return resourceRepositoryPort.create(resourceApplicationMapper.toRepositoryCreateRequest(resource));
    }
}
