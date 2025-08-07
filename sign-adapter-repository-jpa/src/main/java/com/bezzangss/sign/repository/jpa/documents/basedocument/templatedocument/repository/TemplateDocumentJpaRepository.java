package com.bezzangss.sign.repository.jpa.documents.basedocument.templatedocument.repository;

import com.bezzangss.sign.repository.jpa.documents.basedocument.templatedocument.entity.TemplateDocumentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemplateDocumentJpaRepository extends JpaRepository<TemplateDocumentJpaEntity, String>, TemplateDocumentJpaRepositoryCustom {
    Optional<TemplateDocumentJpaEntity> findById(String id);
}
