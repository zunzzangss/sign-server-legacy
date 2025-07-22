package com.bezzangss.sign.repositoryjpa.resources.repository;

import com.bezzangss.sign.repositoryjpa.resources.entity.ResourceJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceJpaRepository extends JpaRepository<ResourceJpaEntity, String>, ResourceJpaRepositoryCustom {
}
