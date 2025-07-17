package com.bezzangss.sign.domain.documents._basedocument._templatedocument.aggregate;

import com.bezzangss.sign.domain.DomainException;
import com.bezzangss.sign.domain.documents._basedocument.BaseDocument;
import com.bezzangss.sign.domain.documents._basedocument.BaseDocumentStatus;
import com.bezzangss.sign.domain.documents._basedocument._templatedocument.dto.TemplateDocumentCreateRequest;
import com.bezzangss.sign.domain.documents._basedocument._templatedocument.dto.TemplateDocumentUpdateRequest;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.UUID;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;
import static com.bezzangss.sign.common.exception.ErrorCode.TEMPLATE_DOCUMENT_IS_NOT_UPDATABLE_EXCEPTION;

@Getter
public class TemplateDocument implements BaseDocument {
    private String id;
    private String name;
    private String description;
    private BaseDocumentStatus status;
    private Instant createdAt;
    private Instant lastModifiedAt;

    @Builder
    private TemplateDocument(String id, String name, String description, BaseDocumentStatus status, Instant createdAt, Instant lastModifiedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.validate();
    }

    public static TemplateDocument create(TemplateDocumentCreateRequest templateDocumentCreateRequest) {
        return TemplateDocument.builder()
                .id(ObjectUtils.isEmpty(templateDocumentCreateRequest.getId()) ? UUID.randomUUID().toString() : templateDocumentCreateRequest.getId())
                .name(templateDocumentCreateRequest.getName())
                .description(templateDocumentCreateRequest.getDescription())
                .status(BaseDocumentStatus.NONE)
                .createdAt(ObjectUtils.isEmpty(templateDocumentCreateRequest.getCreatedAt()) ? Instant.now() : templateDocumentCreateRequest.getCreatedAt())
                .lastModifiedAt(templateDocumentCreateRequest.getLastModifiedAt())
                .build();
    }

    public void update(TemplateDocumentUpdateRequest templateDocumentUpdateRequest) {
        if (!this.updatable()) throw new DomainException(TEMPLATE_DOCUMENT_IS_NOT_UPDATABLE_EXCEPTION, this.id);

        if (templateDocumentUpdateRequest.getName() != null) this.name = templateDocumentUpdateRequest.getName();
        if (templateDocumentUpdateRequest.getDescription() != null) this.description = templateDocumentUpdateRequest.getDescription().orElse(null);
        if (templateDocumentUpdateRequest.getLastModifiedAt() != null) this.lastModifiedAt = templateDocumentUpdateRequest.getLastModifiedAt().orElse(Instant.now());

        this.validate();
    }

    public boolean updatable() {
        return this.status == BaseDocumentStatus.NONE;
    }

    private void validate() {
        if (ObjectUtils.isEmpty(this.id)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");
        if (ObjectUtils.isEmpty(this.name)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "name");
        if (ObjectUtils.isEmpty(this.status)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "status");
        if (ObjectUtils.isEmpty(this.createdAt)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "createdAt");
    }
}
