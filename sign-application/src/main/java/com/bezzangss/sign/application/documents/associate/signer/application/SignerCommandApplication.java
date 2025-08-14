package com.bezzangss.sign.application.documents.associate.signer.application;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.documents.associate.signer.application.mapper.SignerApplicationMapper;
import com.bezzangss.sign.application.documents.associate.signer.port.in.SignerApplicationCommandPort;
import com.bezzangss.sign.application.documents.associate.signer.port.in.dto.request.SignerApplicationCreateRequest;
import com.bezzangss.sign.application.documents.associate.signer.port.out.dto.SignerRepositoryPort;
import com.bezzangss.sign.application.documents.document.application.bridge.DocumentQueryApplicationBridge;
import com.bezzangss.sign.domain.documents.associate.signer.aggregate.Signer;
import com.bezzangss.sign.domain.documents.associate.signer.dto.SignerDomainCreateRequest;
import com.bezzangss.sign.domain.documents.associate.signer.event.SignerDomainEvent;
import com.bezzangss.sign.domain.documents.associate.signer.service.SignerDomainService;
import com.bezzangss.sign.domain.documents.document.aggregate.Document;
import lombok.RequiredArgsConstructor;
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
public class SignerCommandApplication implements SignerApplicationCommandPort {
    private final SignerApplicationMapper signerApplicationMapper;
    private final SignerDomainService signerDomainService;
    private final SignerRepositoryPort signerRepositoryPort;

    private final DocumentQueryApplicationBridge documentQueryApplicationBridge;

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

    private void waits(String id) {
        if (ObjectUtils.isEmpty(id)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");

        Signer signer = this.findById(id);
        Document document = this.findDocumentByDocumentId(signer.getDocumentId());

        signerDomainService.waits(signer, document);
        this.update(signer);
    }

    private void ready(String id) {
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

        signerDomainService.sign(id, signers, document);
        this.update(signers);
    }

    @EventListener
    public void eventListener(SignerDomainEvent signerDomainEvent) {
        switch (signerDomainEvent.getStatus()) {
            case WAITING:
                this.waits(signerDomainEvent.getId());
            case READY:
                this.ready(signerDomainEvent.getId());
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
