package com.bezzangss.sign.repository.jpa.documents.metadocument.groupdocument.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GroupDocumentJpaRepositoryQueryDsl implements GroupDocumentJpaRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
}
