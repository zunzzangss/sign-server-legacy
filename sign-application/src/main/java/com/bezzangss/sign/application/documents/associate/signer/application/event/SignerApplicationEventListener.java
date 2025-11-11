package com.bezzangss.sign.application.documents.associate.signer.application.event;

import com.bezzangss.sign.domain.documents.associate.signer.event.SignerDomainEvent;
import com.bezzangss.sign.domain.documents.document.event.DocumentDomainEvent;

public interface SignerApplicationEventListener {
    void eventListener(SignerDomainEvent signerDomainEvent);

    void eventListener(DocumentDomainEvent documentDomainEvent);
}
