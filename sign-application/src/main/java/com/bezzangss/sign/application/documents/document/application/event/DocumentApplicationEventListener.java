package com.bezzangss.sign.application.documents.document.application.event;

import com.bezzangss.sign.domain.documents.associate.signer.event.SignerDomainEvent;

public interface DocumentApplicationEventListener {
    void eventListener(SignerDomainEvent signerDomainEvent);
}
