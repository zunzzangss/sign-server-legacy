package com.bezzangss.sign.domain.documents.associate.signer.service;

import com.bezzangss.sign.domain.DomainException;
import com.bezzangss.sign.domain.documents.associate.signer.aggregate.Signer;
import com.bezzangss.sign.domain.documents.associate.signer.event.SignerDomainEvent;
import com.bezzangss.sign.domain.documents.associate.signer.service.common.SignerDomainCommonService;
import com.bezzangss.sign.domain.documents.document.aggregate.Document;
import com.bezzangss.sign.domain.documents.document.event.DocumentDomainEvent;
import com.bezzangss.sign.domain.documents.document.service.common.DocumentDomainCommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.bezzangss.sign.common.exception.ErrorCode.SIGNER_ILLEGAL_INTERNAL_SERVER_ERROR;

@RequiredArgsConstructor
@Component
public class SignerDomainService {
    private final DocumentDomainCommonService documentDomainCommonService;
    private final SignerDomainCommonService signerDomainCommonService;

    public void waits(Signer signer, Document document) {
        this.validateForWait(signer, document);
        signer.waits();
    }

    public void ready(Signer signer, Document document) {
        this.validateForReady(signer, document);
        signer.ready();
    }

    public List<ApplicationEvent> sign(String id, List<Signer> signers, Document document) {
        Signer target = signers.stream()
                .filter(signer -> signer.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new DomainException(SIGNER_ILLEGAL_INTERNAL_SERVER_ERROR, id));

        this.validateForSign(target, signers, document);
        target.sign();

        return this.eventsForSign(target, signers, document);
    }

    private List<ApplicationEvent> eventsForSign(Signer signedSigner, List<Signer> signers, Document document) {
        List<ApplicationEvent> events = new ArrayList<>();

        if (signers.stream().noneMatch(Signer::isReady)) {
            signerDomainCommonService.filterByOrder(signers, signedSigner.getOrder() + 1).forEach(signer -> events.add(SignerDomainEvent.ready(signer)));
        }

        if (signers.stream().allMatch(Signer::isSigned)) {
            events.add(DocumentDomainEvent.complete(document));
        }

        return events;
    }

    private void validateForWait(Signer signer, Document document) {
        documentDomainCommonService.validateProcessing(document);
        signerDomainCommonService.validateContains(signer, document);
    }

    private void validateForReady(Signer signer, Document document) {
        documentDomainCommonService.validateProcessing(document);
        signerDomainCommonService.validateContains(signer, document);
    }

    private void validateForSign(Signer signer, List<Signer> signers, Document document) {
        documentDomainCommonService.validateProcessing(document);
        signerDomainCommonService.validateContains(signers, document);
        signerDomainCommonService.validateOrderByReady(signer, signers);
    }
}
