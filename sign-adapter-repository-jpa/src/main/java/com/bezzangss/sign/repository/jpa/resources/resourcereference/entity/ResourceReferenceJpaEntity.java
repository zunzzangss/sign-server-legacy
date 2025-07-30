package com.bezzangss.sign.repository.jpa.resources.resourcereference.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "SI_RESOURCE_REFERENCE")
@Entity
public class ResourceReferenceJpaEntity {
    @Id
    @Column(name = "ID", length = 36)
    private String id;

    @Column(name = "DOMAIN_ID", nullable = false, length = 36)
    private String domainId;

    @Column(name = "DOMAIN", nullable = false, length = 50)
    private String domain;

    @Column(name = "RESOURCE_ID", nullable = false, length = 36)
    private String resourceId;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Instant createdAt;

    @Builder
    public ResourceReferenceJpaEntity(String id, String domainId, String domain, String resourceId, Instant createdAt) {
        this.id = id;
        this.domainId = domainId;
        this.domain = domain;
        this.resourceId = resourceId;
        this.createdAt = createdAt;
    }
}
