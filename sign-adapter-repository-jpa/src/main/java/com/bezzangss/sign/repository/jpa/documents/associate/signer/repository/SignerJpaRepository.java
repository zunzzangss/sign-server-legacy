package com.bezzangss.sign.repository.jpa.documents.associate.signer.repository;

import com.bezzangss.sign.repository.jpa.documents.associate.signer.entity.SignerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SignerJpaRepository extends JpaRepository<SignerJpaEntity, String>, SignerJpaRepositoryCustom {
    Optional<SignerJpaEntity> findById(String id);
}
