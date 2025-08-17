package com.bezzangss.sign.domain.documents.document.event;

import com.bezzangss.sign.domain.DomainException;
import com.bezzangss.sign.domain.documents.document.DocumentStatus;
import com.bezzangss.sign.domain.documents.document.aggregate.Document;
import lombok.Builder;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@Getter
public class DocumentDomainEvent extends ApplicationEvent {
    private final String id;
    private final DocumentStatus status;

    @Builder
    private DocumentDomainEvent(String id, DocumentStatus status) {
        super(UUID.randomUUID().toString());
        this.id = id;
        this.status = status;
        this.validate();
    }

    public static DocumentDomainEvent complete(Document document) {
        return DocumentDomainEvent.builder()
                .id(document.getId())
                .status(DocumentStatus.COMPLETED)
                .build();
    }

    private void validate() {
        if (ObjectUtils.isEmpty(this.id)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");
        if (ObjectUtils.isEmpty(this.status)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "status");
    }
}
