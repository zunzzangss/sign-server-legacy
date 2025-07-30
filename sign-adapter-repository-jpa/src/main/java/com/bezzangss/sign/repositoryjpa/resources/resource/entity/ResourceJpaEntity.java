package com.bezzangss.sign.repositoryjpa.resources.resource.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "SI_RESOURCE")
@Entity
public class ResourceJpaEntity {
    @Id
    @Column(name = "ID", length = 36)
    private String id;

    @Column(name = "TYPE", nullable = false, length = 50)
    private String type;

    @Column(name = "SOURCE", nullable = false, length = 1000)
    private String source;

    @ColumnDefault("0")
    @Column(name = "C_SIZE", nullable = false)
    private long size;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "LAST_MODIFIED_AT")
    private Instant lastModifiedAt;

    @Builder
    public ResourceJpaEntity(String id, String type, String source, long size, Instant createdAt, Instant lastModifiedAt) {
        this.id = id;
        this.type = type;
        this.source = source;
        this.size = size;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }
}
