package com.bezzangss.sign.repository.jpa.documents.document.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DocumentJpaRepositoryQueryDsl implements DocumentJpaRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
}
