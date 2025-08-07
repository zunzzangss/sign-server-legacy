package com.bezzangss.sign.repository.jpa.documents.metadocument.standarddocument.repository;

import com.bezzangss.sign.repository.jpa.documents.metadocument.standarddocument.entity.StandardDocumentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StandardDocumentJpaRepository extends JpaRepository<StandardDocumentJpaEntity, String>, StandardDocumentJpaRepositoryCustom {
    Optional<StandardDocumentJpaEntity> findById(String id);
}
