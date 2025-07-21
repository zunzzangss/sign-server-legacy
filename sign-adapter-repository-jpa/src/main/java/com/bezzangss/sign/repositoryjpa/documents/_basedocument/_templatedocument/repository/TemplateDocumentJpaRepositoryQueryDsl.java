package com.bezzangss.sign.repositoryjpa.documents._basedocument._templatedocument.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TemplateDocumentJpaRepositoryQueryDsl implements TemplateDocumentJpaRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
}
