package com.bezzangss.sign.domain.documents.associate.signer.aggregate;

import com.bezzangss.sign.domain.DomainException;
import com.bezzangss.sign.domain.documents.associate.Associate;
import com.bezzangss.sign.domain.documents.associate.AssociateType;
import com.bezzangss.sign.domain.documents.associate.signer.SignerStatus;
import com.bezzangss.sign.domain.documents.associate.signer.dto.SignerDomainCreateRequest;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.UUID;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@Getter
public class Signer implements Associate {
    private String id;
    private String name;
    private String email;
    private String cellPhone;
    private int order;
    private SignerStatus status;
    private String documentId;
    private Instant createdAt;
    private Instant lastModifiedAt;

    @Builder
    private Signer(String id, String name, String email, String cellPhone, int order, SignerStatus status, String documentId, Instant createdAt, Instant lastModifiedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cellPhone = cellPhone;
        this.order = order;
        this.status = status;
        this.documentId = documentId;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.validate();
    }

    public static Signer create(SignerDomainCreateRequest signerDomainCreateRequest) {
        return Signer.builder()
                .id(ObjectUtils.isEmpty(signerDomainCreateRequest.getId()) ? UUID.randomUUID().toString() : signerDomainCreateRequest.getId())
                .name(signerDomainCreateRequest.getName())
                .email(signerDomainCreateRequest.getEmail())
                .cellPhone(signerDomainCreateRequest.getCellPhone())
                .order(signerDomainCreateRequest.getOrder())
                .status(SignerStatus.NONE)
                .documentId(signerDomainCreateRequest.getDocumentId())
                .createdAt(ObjectUtils.isEmpty(signerDomainCreateRequest.getCreatedAt()) ? Instant.now() : signerDomainCreateRequest.getCreatedAt())
                .lastModifiedAt(signerDomainCreateRequest.getLastModifiedAt())
                .build();
    }

    private void validate() {
        if (ObjectUtils.isEmpty(this.id)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");
        if (ObjectUtils.isEmpty(this.name)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "name");
        if (ObjectUtils.isEmpty(this.status)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "status");
        if (ObjectUtils.isEmpty(this.documentId)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "documentId");
        if (ObjectUtils.isEmpty(this.createdAt)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "createdAt");
    }

    @Override
    public AssociateType getAssociateType() {
        return AssociateType.SIGNER;
    }
}
