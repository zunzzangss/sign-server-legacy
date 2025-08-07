package com.bezzangss.sign.application.documents.associate.publisher.application;

import com.bezzangss.sign.application.documents.associate.publisher.application.mapper.PublisherApplicationMapper;
import com.bezzangss.sign.application.documents.associate.publisher.port.in.PublisherCommandApplicationPort;
import com.bezzangss.sign.application.documents.associate.publisher.port.in.dto.request.PublisherApplicationCreateRequest;
import com.bezzangss.sign.application.documents.associate.publisher.port.out.PublisherRepositoryPort;
import com.bezzangss.sign.domain.documents.associate.publisher.aggregate.Publisher;
import com.bezzangss.sign.domain.documents.associate.publisher.dto.PublisherDomainCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class PublisherCommandApplication implements PublisherCommandApplicationPort {
    private final PublisherApplicationMapper publisherApplicationMapper;
    private final PublisherRepositoryPort publisherRepositoryPort;

    @Override
    public String create(PublisherApplicationCreateRequest publisherApplicationCreateRequest) {
        // TODO: validation

        Publisher publisher = Publisher.create(
                PublisherDomainCreateRequest.builder()
                        .name(publisherApplicationCreateRequest.getName())
                        .email(publisherApplicationCreateRequest.getEmail())
                        .cellPhone(publisherApplicationCreateRequest.getCellPhone())
                        .documentId(publisherApplicationCreateRequest.getDocumentId())
                        .build()
        );

        return publisherRepositoryPort.create(publisherApplicationMapper.toRepositoryCreateRequest(publisher));
    }
}
