package com.bezzangss.sign.application.documents.associate.publisher.application.mapper;

import com.bezzangss.sign.application.documents.associate.publisher.port.in.dto.response.PublisherApplicationResponse;
import com.bezzangss.sign.application.documents.associate.publisher.port.out.dto.request.PublisherRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.associate.publisher.port.out.dto.response.PublisherRepositoryResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.domain.documents.associate.publisher.aggregate.Publisher;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface PublisherApplicationMapper extends CommonMapper {
    PublisherRepositoryCreateRequest toRepositoryCreateRequest(Publisher publisher);

    PublisherApplicationResponse toApplicationResponse(PublisherRepositoryResponse publisherRepositoryResponse);
}
