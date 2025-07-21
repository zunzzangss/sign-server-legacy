package com.bezzangss.sign.application.documents._basedocument._templatedocument.application;

import com.bezzangss.sign.application.documents._basedocument._templatedocument.application.mapper.TemplateDocumentApplicationMapper;
import com.bezzangss.sign.application.documents._basedocument._templatedocument.port.in.TemplateDocumentCommandApplicationPort;
import com.bezzangss.sign.application.documents._basedocument._templatedocument.port.in.dto.request.TemplateDocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents._basedocument._templatedocument.port.out.TemplateDocumentRepositoryPort;
import com.bezzangss.sign.domain.documents._basedocument._templatedocument.aggregate.TemplateDocument;
import com.bezzangss.sign.domain.documents._basedocument._templatedocument.dto.TemplateDocumentDomainCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class TemplateDocumentCommandApplication implements TemplateDocumentCommandApplicationPort {
    private final TemplateDocumentRepositoryPort templateDocumentRepositoryOutPort;

    private final TemplateDocumentApplicationMapper templateDocumentServiceMapper;

    @Override
    public String create(TemplateDocumentApplicationCreateRequest templateDocumentApplicationCreateRequest) {
        TemplateDocument templateDocument = TemplateDocument.create(
                TemplateDocumentDomainCreateRequest.builder()
                        .name(templateDocumentApplicationCreateRequest.getName())
                        .description(templateDocumentApplicationCreateRequest.getDescription())
                        .build()
        );

        return templateDocumentRepositoryOutPort.create(templateDocumentServiceMapper.toRepositoryCreateRequest(templateDocument));
    }
}
