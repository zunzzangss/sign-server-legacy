package com.bezzangss.sign.web.internal.resources.resource;

import com.bezzangss.sign.application.resources.resource.port.in.ResourceCommandApplicationPort;
import com.bezzangss.sign.application.resources.resource.port.in.ResourceQueryApplicationPort;
import com.bezzangss.sign.application.resources.resource.port.in.dto.request.ResourceApplicationCreateRequest;
import com.bezzangss.sign.common.inputstream.InputStreamHandler;
import com.bezzangss.sign.web.dto.response.WebResponse;
import com.bezzangss.sign.web.internal.InternalWebException;
import com.bezzangss.sign.web.internal.resources.resource.dto.request.ResourceInternalWebCreateByMultipartFileRequest;
import com.bezzangss.sign.web.internal.resources.resource.dto.response.ResourceInternalWebResponse;
import com.bezzangss.sign.web.internal.resources.resource.mapper.ResourceInternalWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.bezzangss.sign.common.exception.ErrorCode.RESOURCE_NOT_FOUND_EXCEPTION;

@RequiredArgsConstructor
@Component
public class ResourceInternalWebHandler {
    private final ResourceInternalWebMapper resourceInternalWebMapper;

    private final ResourceCommandApplicationPort resourceCommandApplicationPort;
    private final ResourceQueryApplicationPort resourceQueryApplicationPort;

    @Transactional
    public WebResponse<ResourceInternalWebResponse> createByMultipartFile(ResourceInternalWebCreateByMultipartFileRequest resourceInternalWebCreateByMultipartFileRequest) {
        String id = resourceCommandApplicationPort.create(
                ResourceApplicationCreateRequest.builder()
                        .type("FILE_SYSTEM")
                        .inputStreamHandler(InputStreamHandler.create(() -> resourceInternalWebCreateByMultipartFileRequest.getFile().getInputStream()))
                        .build()
        );

        ResourceInternalWebResponse resourceInternalWebResponse = resourceQueryApplicationPort.findById(id)
                .map(resourceInternalWebMapper::toResponse)
                .orElseThrow(() -> new InternalWebException(RESOURCE_NOT_FOUND_EXCEPTION, id));

        return WebResponse.success(resourceInternalWebResponse);
    }
}
