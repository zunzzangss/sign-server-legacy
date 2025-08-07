package com.bezzangss.sign.repository.jpa.documents.associate.signer.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SignerJpaRepositoryQueryDsl implements SignerJpaRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
}
