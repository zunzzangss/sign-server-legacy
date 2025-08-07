package com.bezzangss.sign.repository.jpa.documents.basedocument.templatedocument.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TemplateDocumentJpaRepositoryQueryDsl implements TemplateDocumentJpaRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
}
