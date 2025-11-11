package com.bezzangss.sign.application.documents.associate.signer.application;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.documents.associate.signer.application.bridge.SignerCommandApplicationBridge;
import com.bezzangss.sign.application.documents.associate.signer.application.event.SignerApplicationEventListener;
import com.bezzangss.sign.application.documents.associate.signer.application.mapper.SignerApplicationMapper;
import com.bezzangss.sign.application.documents.associate.signer.port.in.SignerApplicationCommandPort;
import com.bezzangss.sign.application.documents.associate.signer.port.in.dto.request.SignerApplicationCreateRequest;
import com.bezzangss.sign.application.documents.associate.signer.port.out.dto.SignerRepositoryPort;
import com.bezzangss.sign.application.documents.document.application.bridge.DocumentQueryApplicationBridge;
import com.bezzangss.sign.domain.documents.associate.signer.SignerStatus;
import com.bezzangss.sign.domain.documents.associate.signer.aggregate.Signer;
import com.bezzangss.sign.domain.documents.associate.signer.dto.SignerDomainCreateRequest;
import com.bezzangss.sign.domain.documents.associate.signer.event.SignerDomainEvent;
import com.bezzangss.sign.domain.documents.associate.signer.service.SignerDomainService;
import com.bezzangss.sign.domain.documents.associate.signer.service.common.SignerDomainCommonService;
import com.bezzangss.sign.domain.documents.document.DocumentStatus;
import com.bezzangss.sign.domain.documents.document.aggregate.Document;
import com.bezzangss.sign.domain.documents.document.event.DocumentDomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.bezzangss.sign.common.exception.ErrorCode.*;

@RequiredArgsConstructor
@Transactional
@Component
public class SignerCommandApplication implements SignerApplicationCommandPort, SignerCommandApplicationBridge, SignerApplicationEventListener {
    private final SignerApplicationMapper signerApplicationMapper;
    private final SignerDomainService signerDomainService;
    private final SignerDomainCommonService signerDomainCommonService;
    private final SignerRepositoryPort signerRepositoryPort;

    private final DocumentQueryApplicationBridge documentQueryApplicationBridge;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public String create(SignerApplicationCreateRequest signerApplicationCreateRequest) {
        // TODO: validation

        Signer signer = Signer.create(
                SignerDomainCreateRequest.builder()
                        .name(signerApplicationCreateRequest.getName())
                        .email(signerApplicationCreateRequest.getEmail())
                        .cellPhone(signerApplicationCreateRequest.getCellPhone())
                        .order(signerApplicationCreateRequest.getOrder())
                        .documentId(signerApplicationCreateRequest.getDocumentId())
                        .build()
        );

        return signerRepositoryPort.create(signerApplicationMapper.toRepositoryCreateRequest(signer));
    }

    @Override
    public void waits(String id) {
        if (ObjectUtils.isEmpty(id)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");

        Signer signer = this.findById(id);
        Document document = this.findDocumentByDocumentId(signer.getDocumentId());

        signerDomainService.waits(signer, document);
        this.update(signer);
    }

    @Override
    public void ready(String id) {
        if (ObjectUtils.isEmpty(id)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");

        Signer signer = this.findById(id);
        Document document = this.findDocumentByDocumentId(signer.getDocumentId());

        signerDomainService.ready(signer, document);
        this.update(signer);
    }

    @Override
    public void sign(String id) {
        if (ObjectUtils.isEmpty(id)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");

        Signer signer = this.findById(id);
        Document document = this.findDocumentByDocumentId(signer.getDocumentId());
        List<Signer> signers = this.findAllByDocumentId(signer.getDocumentId());

        ApplicationEvent event = signerDomainService.sign(signer, signers, document);
        this.update(signer);

        eventPublisher.publishEvent(event);
    }

    @EventListener
    @Override
    public void eventListener(SignerDomainEvent signerDomainEvent) {
        String id = signerDomainEvent.getId();
        SignerStatus status = signerDomainEvent.getStatus();

        switch (status) {
            case SIGNED:
                Signer signer = this.findById(id);
                List<Signer> signers = this.findAllByDocumentId(signer.getDocumentId());

                if (signers.stream().noneMatch(Signer::isReady)) {
                    signerDomainCommonService.filterByOrder(signers, signer.getOrder() + 1).forEach(s -> this.ready(s.getId()));
                }

                break;
            default:
                break;
        }
    }

    @EventListener
    @Override
    public void eventListener(DocumentDomainEvent documentDomainEvent) {
        String documentId = documentDomainEvent.getId();
        DocumentStatus documentStatus = documentDomainEvent.getStatus();

        List<Signer> signers = this.findAllByDocumentId(documentId);

        switch (documentStatus) {
            case PROCESSING:
                signers.forEach(signer -> this.waits(signer.getId()));
                signerDomainCommonService.filterByMinOrder(signers).forEach(signer -> this.ready(signer.getId()));

                break;
            default:
                break;
        }
    }

    private Signer findById(String id) {
        return signerRepositoryPort.findById(id)
                .map(signerApplicationMapper::toDomain)
                .orElseThrow(() -> new ApplicationException(SIGNER_NOT_FOUND_EXCEPTION, "id"));
    }

    private List<Signer> findAllByDocumentId(String documentId) {
        return signerRepositoryPort.findAllByDocumentId(documentId).stream()
                .map(signerApplicationMapper::toDomain)
                .collect(Collectors.toList());
    }

    private Document findDocumentByDocumentId(String documentId) {
        return documentQueryApplicationBridge.findDomainById(documentId).orElseThrow(() -> new ApplicationException(DOCUMENT_NOT_FOUND_EXCEPTION, documentId));
    }

    private String update(Signer signer) {
        return signerRepositoryPort.update(signerApplicationMapper.toRepositoryUpdateRequest(signer));
    }

    private List<String> update(List<Signer> signers) {
        return signerRepositoryPort.updateAll(
                signers.stream()
                        .map(signerApplicationMapper::toRepositoryUpdateRequest)
                        .collect(Collectors.toList())
        );
    }
}
