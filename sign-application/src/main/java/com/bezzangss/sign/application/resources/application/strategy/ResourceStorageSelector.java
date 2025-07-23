package com.bezzangss.sign.application.resources.application.strategy;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.resources.port.out.ResourceStoragePort;
import com.bezzangss.sign.domain.resource.ResourceType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.bezzangss.sign.common.exception.ErrorCode.RESOURCE_ILLEGAL_TYPE_EXCEPTION;

@Component
public class ResourceStorageSelector {
    private final Map<ResourceType, ResourceStoragePort> strategyMap;

    public ResourceStorageSelector(List<ResourceStoragePort> strategies) {
        this.strategyMap = strategies.stream().collect(Collectors.toMap(strategy -> ResourceType.from(strategy.resourceType()), Function.identity()));
    }

    public ResourceStoragePort select(ResourceType type) {
        return Optional.ofNullable(strategyMap.get(type)).orElseThrow(() -> new ApplicationException(RESOURCE_ILLEGAL_TYPE_EXCEPTION, type.name()));
    }

    public ResourceStoragePort select(String type) {
        return this.select(ResourceType.from(type));
    }
}
