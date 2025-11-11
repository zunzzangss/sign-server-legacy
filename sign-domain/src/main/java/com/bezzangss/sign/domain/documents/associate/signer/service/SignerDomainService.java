package com.bezzangss.sign.domain.documents.associate.signer.service;

import com.bezzangss.sign.domain.documents.associate.signer.aggregate.Signer;
import com.bezzangss.sign.domain.documents.associate.signer.event.SignerDomainEvent;
import com.bezzangss.sign.domain.documents.associate.signer.service.common.SignerDomainCommonService;
import com.bezzangss.sign.domain.documents.document.aggregate.Document;
import com.bezzangss.sign.domain.documents.document.service.common.DocumentDomainCommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public ApplicationEvent sign(Signer signer, List<Signer> signers, Document document) {
        signerDomainCommonService.validateContains(signer, signers);
        this.validateForSign(signer, signers, document);
        signer.sign();

        return SignerDomainEvent.signed(signer);
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
        signerDomainCommonService.validateContains(signer, signers);
        signerDomainCommonService.validateContains(signers, document);
        signerDomainCommonService.validateOrderByReady(signer, signers);
    }
}
