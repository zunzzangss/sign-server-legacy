package com.bezzangss.sign.application.documents.associate.signer.application.event;

import com.bezzangss.sign.domain.documents.associate.signer.event.SignerDomainEvent;

public interface SignerApplicationEvent {
    void eventListener(SignerDomainEvent signerDomainEvent);
}
