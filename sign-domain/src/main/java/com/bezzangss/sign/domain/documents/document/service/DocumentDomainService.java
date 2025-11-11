package com.bezzangss.sign.domain.documents.document.service;

import com.bezzangss.sign.domain.documents.associate.signer.aggregate.Signer;
import com.bezzangss.sign.domain.documents.associate.signer.service.common.SignerDomainCommonService;
import com.bezzangss.sign.domain.documents.document.aggregate.Document;
import com.bezzangss.sign.domain.documents.document.event.DocumentDomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DocumentDomainService {
    private final SignerDomainCommonService signerDomainCommonService;

    public ApplicationEvent process(List<Signer> signers, Document document) {
        this.validateForProcess(signers, document);
        document.process();

        return DocumentDomainEvent.processing(document);
    }

    public void complete(List<Signer> signers, Document document) {
        this.validateForComplete(signers, document);
        document.complete();
    }

    private void validateForProcess(List<Signer> signers, Document document) {
        signerDomainCommonService.validateContains(signers, document);
        signerDomainCommonService.validateAllMatchNone(signers);
    }

    private void validateForComplete(List<Signer> signers, Document document) {
        signerDomainCommonService.validateContains(signers, document);
        signerDomainCommonService.validateAllMatchSigned(signers);
    }
}
