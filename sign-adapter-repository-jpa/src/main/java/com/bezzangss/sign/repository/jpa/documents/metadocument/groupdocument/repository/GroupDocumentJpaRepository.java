package com.bezzangss.sign.repository.jpa.documents.metadocument.groupdocument.repository;

import com.bezzangss.sign.repository.jpa.documents.metadocument.groupdocument.entity.GroupDocumentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupDocumentJpaRepository extends JpaRepository<GroupDocumentJpaEntity, String>, GroupDocumentJpaRepositoryCustom {
    Optional<GroupDocumentJpaEntity> findById(String id);
}
