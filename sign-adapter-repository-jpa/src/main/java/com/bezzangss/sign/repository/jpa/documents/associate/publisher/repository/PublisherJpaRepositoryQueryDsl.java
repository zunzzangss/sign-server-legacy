package com.bezzangss.sign.repository.jpa.documents.associate.publisher.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PublisherJpaRepositoryQueryDsl implements PublisherJpaRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
}
