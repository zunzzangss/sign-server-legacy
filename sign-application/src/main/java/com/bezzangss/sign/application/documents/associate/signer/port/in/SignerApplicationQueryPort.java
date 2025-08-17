package com.bezzangss.sign.application.documents.associate.signer.port.in;

import com.bezzangss.sign.application.documents.associate.signer.port.in.dto.response.SignerApplicationResponse;

import java.util.Optional;

public interface SignerApplicationQueryPort {
    Optional<SignerApplicationResponse> findById(String id);
}
