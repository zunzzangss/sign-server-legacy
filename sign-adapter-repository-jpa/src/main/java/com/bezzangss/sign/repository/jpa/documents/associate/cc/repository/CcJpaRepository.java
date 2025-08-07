package com.bezzangss.sign.repository.jpa.documents.associate.cc.repository;

import com.bezzangss.sign.repository.jpa.documents.associate.cc.entity.CcJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CcJpaRepository extends JpaRepository<CcJpaEntity, String>, CcJpaRepositoryCustom {
    Optional<CcJpaEntity> findById(String id);
}
