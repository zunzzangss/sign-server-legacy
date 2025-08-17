package com.bezzangss.sign.web.internal.documents.associate.signer;

import com.bezzangss.sign.application.documents.associate.signer.port.in.SignerApplicationCommandPort;
import com.bezzangss.sign.application.documents.associate.signer.port.in.SignerApplicationQueryPort;
import com.bezzangss.sign.web.dto.response.WebResponse;
import com.bezzangss.sign.web.internal.InternalWebException;
import com.bezzangss.sign.web.internal.documents.associate.signer.dto.response.SignerInternalWebResponse;
import com.bezzangss.sign.web.internal.documents.associate.signer.mapper.SignerInternalWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.bezzangss.sign.common.exception.ErrorCode.SIGNER_NOT_FOUND_EXCEPTION;

@RequiredArgsConstructor
@Component
public class SignerInternalWebHandler {
    private final SignerInternalWebMapper signerInternalWebMapper;
    private final SignerApplicationQueryPort signerApplicationQueryPort;
    private final SignerApplicationCommandPort signerApplicationCommandPort;

    public WebResponse<SignerInternalWebResponse> sign(String id) {
        signerApplicationCommandPort.sign(id);

        SignerInternalWebResponse signerInternalWebResponse = signerApplicationQueryPort.findById(id)
                .map(signerInternalWebMapper::toResponse)
                .orElseThrow(() -> new InternalWebException(SIGNER_NOT_FOUND_EXCEPTION, id));

        return WebResponse.success(signerInternalWebResponse);
    }
}
