package com.bezzangss.sign.application.resources.application;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.resources.application.mapper.ResourceApplicationMapper;
import com.bezzangss.sign.application.resources.application.strategy.ResourceStorageSelector;
import com.bezzangss.sign.application.resources.port.in.ResourceQueryApplicationPort;
import com.bezzangss.sign.application.resources.port.out.ResourceRepositoryPort;
import com.bezzangss.sign.common.resource.ResourceStream;
import com.bezzangss.sign.domain.resource.aggregate.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;
import static com.bezzangss.sign.common.exception.ErrorCode.RESOURCE_NOT_FOUND_EXCEPTION;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class ResourceQueryApplication implements ResourceQueryApplicationPort {
    private final ResourceApplicationMapper resourceApplicationMapper;
    private final ResourceRepositoryPort resourceRepositoryPort;
    private final ResourceStorageSelector resourceStorageSelector;

    @Override
    public ResourceStream readById(String id) {
        if (ObjectUtils.isEmpty(id)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");

        Resource resource = resourceRepositoryPort.findById(id)
                .map(resourceApplicationMapper::toDomain)
                .orElseThrow(() -> new ApplicationException(RESOURCE_NOT_FOUND_EXCEPTION, "id"));

        return resourceStorageSelector.select(resource.getType()).read(resource.getSource());
    }
}
