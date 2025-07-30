package com.bezzangss.sign.repository.jpa.resources.resource.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResourceJpaRepositoryQueryDsl implements ResourceJpaRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
}
