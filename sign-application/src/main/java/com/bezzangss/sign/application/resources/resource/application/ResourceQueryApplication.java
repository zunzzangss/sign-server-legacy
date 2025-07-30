package com.bezzangss.sign.application.resources.resource.application;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.resources.resource.application.mapper.ResourceApplicationMapper;
import com.bezzangss.sign.application.resources.resource.port.in.ResourceQueryApplicationPort;
import com.bezzangss.sign.application.resources.resource.port.in.dto.response.ResourceApplicationResponse;
import com.bezzangss.sign.application.resources.resource.port.out.ResourceRepositoryPort;
import com.bezzangss.sign.application.storage.application.bridge.StorageQueryApplicationBridge;
import com.bezzangss.sign.application.storage.application.bridge.dto.request.StorageApplicationReadRequest;
import com.bezzangss.sign.common.inputstream.InputStreamHandler;
import com.bezzangss.sign.domain.resources.resource.aggregate.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;
import static com.bezzangss.sign.common.exception.ErrorCode.RESOURCE_NOT_FOUND_EXCEPTION;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class ResourceQueryApplication implements ResourceQueryApplicationPort {
    private final ResourceApplicationMapper resourceApplicationMapper;
    private final ResourceRepositoryPort resourceRepositoryPort;

    private final StorageQueryApplicationBridge storageQueryApplicationBridge;

    @Override
    public Optional<ResourceApplicationResponse> findById(String id) {
        if (ObjectUtils.isEmpty(id)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");

        return resourceRepositoryPort.findById(id).map(resourceApplicationMapper::toApplicationResponse);
    }

    @Override
    public InputStreamHandler readById(String id) {
        if (ObjectUtils.isEmpty(id)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");

        Resource resource = resourceRepositoryPort.findById(id)
                .map(resourceApplicationMapper::toDomain)
                .orElseThrow(() -> new ApplicationException(RESOURCE_NOT_FOUND_EXCEPTION, "id"));

        return storageQueryApplicationBridge.read(
                StorageApplicationReadRequest.builder()
                        .typeProvider(resource.getType())
                        .source(resource.getSource())
                        .build()
        );
    }
}
