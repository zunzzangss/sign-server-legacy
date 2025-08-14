package com.bezzangss.sign.application.documents.associate.signer.application.bridge;

import com.bezzangss.sign.domain.documents.associate.signer.aggregate.Signer;

import java.util.List;

public interface SignerQueryApplicationBridge {
    List<Signer> findAllDomainByDocumentId(String documentId);
}
