package com.bezzangss.sign.repositoryjpa.resources.resource.repository;

import com.bezzangss.sign.repositoryjpa.resources.resource.entity.ResourceJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResourceJpaRepository extends JpaRepository<ResourceJpaEntity, String>, ResourceJpaRepositoryCustom {
    Optional<ResourceJpaEntity> findById(String id);
}
