package com.bezzangss.sign.domain.documents.document.service;

import com.bezzangss.sign.domain.documents.associate.signer.aggregate.Signer;
import com.bezzangss.sign.domain.documents.associate.signer.event.SignerDomainEvent;
import com.bezzangss.sign.domain.documents.associate.signer.service.common.SignerDomainCommonService;
import com.bezzangss.sign.domain.documents.document.aggregate.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DocumentDomainService {
    private final SignerDomainCommonService signerDomainCommonService;

    private final ApplicationEventPublisher eventPublisher;

    public void process(List<Signer> signers, Document document) {
        this.validateForProcess(signers, document);
        document.process();
        this.publishEventForProcess(signers);
    }

    private void validateForProcess(List<Signer> signers, Document document) {
        signerDomainCommonService.validateAllMatchNone(signers);
        signerDomainCommonService.validateContains(signers, document);
    }

    private void publishEventForProcess(List<Signer> signers) {
        signers.forEach(signer -> eventPublisher.publishEvent(SignerDomainEvent.waits(signer.getId())));
        signerDomainCommonService.filterByMinOrder(signers).forEach(signer -> eventPublisher.publishEvent(SignerDomainEvent.ready(signer.getId())));
    }
}
