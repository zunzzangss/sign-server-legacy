package com.bezzangss.sign.repositoryjpa.documents._basedocument._templatedocument.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "SI_TEMPLATE_DOCUMENT")
@Entity
public class TemplateDocumentJpaEntity implements Persistable<String> {
    @Id
    @Column(name = "ID", length = 36)
    private String id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @ColumnDefault("'NONE'")
    @Column(name = "STATUS", nullable = false, length = 50)
    private String status;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "LAST_MODIFIED_AT")
    private Instant lastModifiedAt;

    @Builder
    public TemplateDocumentJpaEntity(String id, String name, String description, String status, Instant createdAt, Instant lastModifiedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }

    @Override
    public boolean isNew() {
        return this.id == null && this.createdAt == null;
    }
}
