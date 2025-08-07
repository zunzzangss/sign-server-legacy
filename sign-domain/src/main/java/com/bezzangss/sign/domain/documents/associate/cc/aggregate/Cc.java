package com.bezzangss.sign.domain.documents.associate.cc.aggregate;

import com.bezzangss.sign.domain.DomainException;
import com.bezzangss.sign.domain.documents.associate.Associate;
import com.bezzangss.sign.domain.documents.associate.AssociateType;
import com.bezzangss.sign.domain.documents.associate.cc.dto.CcDomainCreateRequest;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.UUID;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@Getter
public class Cc implements Associate {
    private String id;
    private String name;
    private String email;
    private String cellPhone;
    private String documentId;
    private Instant createdAt;
    private Instant lastModifiedAt;

    @Builder
    private Cc(String id, String name, String email, String cellPhone, String documentId, Instant createdAt, Instant lastModifiedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cellPhone = cellPhone;
        this.documentId = documentId;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.validate();
    }

    public static Cc create(CcDomainCreateRequest ccDomainCreateRequest) {
        return Cc.builder()
                .id(ObjectUtils.isEmpty(ccDomainCreateRequest.getId()) ? UUID.randomUUID().toString() : ccDomainCreateRequest.getId())
                .name(ccDomainCreateRequest.getName())
                .email(ccDomainCreateRequest.getEmail())
                .cellPhone(ccDomainCreateRequest.getCellPhone())
                .documentId(ccDomainCreateRequest.getDocumentId())
                .createdAt(ObjectUtils.isEmpty(ccDomainCreateRequest.getCreatedAt()) ? Instant.now() : ccDomainCreateRequest.getCreatedAt())
                .lastModifiedAt(ccDomainCreateRequest.getLastModifiedAt())
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
        return AssociateType.CC;
    }
}
