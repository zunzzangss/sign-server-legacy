package com.bezzangss.sign.domain.documents.associate.publisher.aggregate;

import com.bezzangss.sign.domain.DomainException;
import com.bezzangss.sign.domain.documents.associate.Associate;
import com.bezzangss.sign.domain.documents.associate.AssociateType;
import com.bezzangss.sign.domain.documents.associate.publisher.dto.PublisherDomainCreateRequest;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.UUID;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@Getter
public class Publisher implements Associate {
    private String id;
    private String name;
    private String email;
    private String cellPhone;
    private String documentId;
    private Instant createdAt;
    private Instant lastModifiedAt;

    @Builder
    private Publisher(String id, String name, String email, String cellPhone, String documentId, Instant createdAt, Instant lastModifiedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cellPhone = cellPhone;
        this.documentId = documentId;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.validate();
    }

    public static Publisher create(PublisherDomainCreateRequest publisherDomainCreateRequest) {
        return Publisher.builder()
                .id(ObjectUtils.isEmpty(publisherDomainCreateRequest.getId()) ? UUID.randomUUID().toString() : publisherDomainCreateRequest.getId())
                .name(publisherDomainCreateRequest.getName())
                .email(publisherDomainCreateRequest.getEmail())
                .cellPhone(publisherDomainCreateRequest.getCellPhone())
                .documentId(publisherDomainCreateRequest.getDocumentId())
                .createdAt(ObjectUtils.isEmpty(publisherDomainCreateRequest.getCreatedAt()) ? Instant.now() : publisherDomainCreateRequest.getCreatedAt())
                .lastModifiedAt(publisherDomainCreateRequest.getLastModifiedAt())
                .build();
    }

    private void validate() {
        if (ObjectUtils.isEmpty(this.id)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");
        if (ObjectUtils.isEmpty(this.name)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "name");
        if (ObjectUtils.isEmpty(this.documentId)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "documentId");
        if (ObjectUtils.isEmpty(this.createdAt)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "createdAt");
    }

    @Override
    public AssociateType getAssociateType() {
        return AssociateType.PUBLISHER;
    }
}
