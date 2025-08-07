package com.bezzangss.sign.repository.jpa.documents.associate.publisher.mapper;

import com.bezzangss.sign.application.documents.associate.publisher.port.out.dto.request.PublisherRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.associate.publisher.port.out.dto.response.PublisherRepositoryResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.repository.jpa.documents.associate.publisher.entity.PublisherJpaEntity;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface PublisherJpaMapper extends CommonMapper {
    PublisherJpaEntity toEntity(PublisherRepositoryCreateRequest publisherRepositoryCreateRequest);

    PublisherRepositoryResponse toResponse(PublisherJpaEntity publisherJpaEntity);
}
