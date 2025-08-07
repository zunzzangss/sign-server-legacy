package com.bezzangss.sign.application.documents.associate.signer.port.in;

import com.bezzangss.sign.application.documents.associate.signer.port.in.dto.request.SignerApplicationCreateRequest;

public interface SignerApplicationCommandPort {
    String create(SignerApplicationCreateRequest signerApplicationCreateRequest);
}
