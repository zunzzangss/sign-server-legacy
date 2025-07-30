package com.bezzangss.sign.repository.jpa.resources.resourcereference.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResourceReferenceJpaRepositoryQueryDsl implements ResourceReferenceJpaRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
}
