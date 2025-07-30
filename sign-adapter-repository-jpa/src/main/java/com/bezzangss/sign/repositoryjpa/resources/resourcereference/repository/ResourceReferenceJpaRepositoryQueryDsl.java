package com.bezzangss.sign.repositoryjpa.resources.resourcereference.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResourceReferenceJpaRepositoryQueryDsl implements ResourceReferenceJpaRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
}
