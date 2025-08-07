package com.bezzangss.sign.repository.jpa.documents.associate.cc.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CcJpaRepositoryQueryDsl implements CcJpaRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
}
