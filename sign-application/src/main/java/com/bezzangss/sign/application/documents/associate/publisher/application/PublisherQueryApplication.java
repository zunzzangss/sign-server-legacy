package com.bezzangss.sign.application.documents.associate.publisher.application;

import com.bezzangss.sign.application.documents.associate.publisher.application.mapper.PublisherApplicationMapper;
import com.bezzangss.sign.application.documents.associate.publisher.port.in.PublisherQueryApplicationPort;
import com.bezzangss.sign.application.documents.associate.publisher.port.out.PublisherRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class PublisherQueryApplication implements PublisherQueryApplicationPort {
    private final PublisherApplicationMapper publisherApplicationMapper;
    private final PublisherRepositoryPort publisherRepositoryPort;
}
