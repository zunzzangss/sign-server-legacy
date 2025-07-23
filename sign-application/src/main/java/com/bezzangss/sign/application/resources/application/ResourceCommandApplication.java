package com.bezzangss.sign.application.resources.application;

import com.bezzangss.sign.application.resources.application.mapper.ResourceApplicationMapper;
import com.bezzangss.sign.application.resources.application.strategy.ResourceStorageSelector;
import com.bezzangss.sign.application.resources.port.in.ResourceCommandApplicationPort;
import com.bezzangss.sign.application.resources.port.in.dto.request.ResourceApplicationCreateRequest;
import com.bezzangss.sign.application.resources.port.out.ResourceRepositoryPort;
import com.bezzangss.sign.application.resources.port.out.dto.request.ResourceStorageWriteRequest;
import com.bezzangss.sign.application.resources.port.out.dto.response.ResourceStorageWriteResponse;
import com.bezzangss.sign.domain.resource.ResourceType;
import com.bezzangss.sign.domain.resource.aggregate.Resource;
import com.bezzangss.sign.domain.resource.dto.ResourceCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Component
public class ResourceCommandApplication implements ResourceCommandApplicationPort {
    private final ResourceApplicationMapper resourceApplicationMapper;
    private final ResourceRepositoryPort resourceRepositoryPort;
    private final ResourceStorageSelector resourceStorageSelector;

    @Override
    public String create(ResourceApplicationCreateRequest resourceApplicationCreateRequest) {
        String id = ObjectUtils.isEmpty(resourceApplicationCreateRequest.getId()) ? UUID.randomUUID().toString() : resourceApplicationCreateRequest.getId();
        String type = resourceApplicationCreateRequest.getType();

        ResourceStorageWriteResponse resourceStorageWriteResponse = resourceStorageSelector
                .select(type)
                .write(
                        ResourceStorageWriteRequest.builder()
                                .id(id)
                                .source(resourceApplicationCreateRequest.getSource())
                                .resourceStream(resourceApplicationCreateRequest.getResourceStream())
                                .build()
                );

        Resource resource = Resource.create(
                ResourceCreateRequest.builder()
                        .id(id)
                        .type(ResourceType.from(type))
                        .source(resourceStorageWriteResponse.getSource())
                        .size(resourceStorageWriteResponse.getSize())
                        .build()
        );

        return resourceRepositoryPort.create(resourceApplicationMapper.toRepositoryCreateRequest(resource));
    }
}
