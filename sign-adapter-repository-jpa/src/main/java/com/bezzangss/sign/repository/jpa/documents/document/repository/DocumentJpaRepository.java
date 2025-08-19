package com.bezzangss.sign.repository.jpa.documents.document.repository;

import com.bezzangss.sign.repository.jpa.documents.document.entity.DocumentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentJpaRepository extends JpaRepository<DocumentJpaEntity, String>, DocumentJpaRepositoryCustom {
    Optional<DocumentJpaEntity> findById(String id);

    Optional<DocumentJpaEntity> findByMetaDocumentTypeAndMetaDocumentId(String metaDocumentType, String metaDocumentId);

    List<DocumentJpaEntity> findAllByMetaDocumentTypeAndMetaDocumentId(String metaDocumentType, String metaDocumentId);
}
