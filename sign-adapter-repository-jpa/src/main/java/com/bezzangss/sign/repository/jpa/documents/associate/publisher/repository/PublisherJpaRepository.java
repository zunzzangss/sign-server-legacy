package com.bezzangss.sign.repository.jpa.documents.associate.publisher.repository;

import com.bezzangss.sign.repository.jpa.documents.associate.publisher.entity.PublisherJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherJpaRepository extends JpaRepository<PublisherJpaEntity, String>, PublisherJpaRepositoryCustom {
    Optional<PublisherJpaEntity> findById(String id);

    Optional<PublisherJpaEntity> findByDocumentId(String documentId);
}
