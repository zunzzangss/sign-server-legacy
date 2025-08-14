package com.bezzangss.sign.application.documents.document.application.event;

import com.bezzangss.sign.domain.documents.document.event.DocumentDomainEvent;

public interface DocumentApplicationEvent {
    void eventListener(DocumentDomainEvent documentDomainEvent);
}
