package com.bezzangss.sign.repositoryjpa.resources.resourcereference.repository;

import com.bezzangss.sign.repositoryjpa.resources.resourcereference.entity.ResourceReferenceJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceReferenceJpaRepository extends JpaRepository<ResourceReferenceJpaEntity, String>, ResourceReferenceJpaRepositoryCustom {
    Optional<ResourceReferenceJpaEntity> findByDomainIdAndDomain(String domainId, String domain);

    List<ResourceReferenceJpaEntity> findAllByDomainIdAndDomain(String domainId, String domain);
}
