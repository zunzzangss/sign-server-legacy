package com.bezzangss.sign.repository.jpa.documents.associate.signer.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "SI_SIGNER")
@Entity
public class SignerJpaEntity {
    @Id
    @Column(name = "ID", length = 36)
    private String id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "CELL_PHONE", length = 11)
    private String cellPhone;

    @Column(name = "C_ORDER", nullable = false)
    private int order;

    @Column(name = "STATUS", nullable = false, length = 50)
    private String status;

    @Column(name = "DOCUMENT_ID", nullable = false, length = 36)
    private String documentId;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "LAST_MODIFIED_AT")
    private Instant lastModifiedAt;

    @Builder
    public SignerJpaEntity(String id, String name, String email, String cellPhone, int order, String status, String documentId, Instant createdAt, Instant lastModifiedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cellPhone = cellPhone;
        this.order = order;
        this.status = status;
        this.documentId = documentId;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }
}
