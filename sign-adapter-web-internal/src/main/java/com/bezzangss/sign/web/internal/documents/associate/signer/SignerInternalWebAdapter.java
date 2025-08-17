package com.bezzangss.sign.web.internal.documents.associate.signer;

import com.bezzangss.sign.web.dto.response.WebResponse;
import com.bezzangss.sign.web.internal.documents.associate.signer.dto.response.SignerInternalWebResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SignerInternalWebAdapter {
    private final SignerInternalWebHandler signerInternalWebHandler;

    @PostMapping(value = "/internal/v1/signer/{id}/sign", consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<SignerInternalWebResponse> sign(
            @PathVariable String id
    ) {
        return signerInternalWebHandler.sign(id);
    }
}
