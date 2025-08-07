package com.bezzangss.sign.repository.jpa.documents.metadocument.standarddocument.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StandardDocumentJpaRepositoryQueryDsl implements StandardDocumentJpaRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
}
