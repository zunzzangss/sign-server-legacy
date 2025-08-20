package com.bezzangss.sign.web.internal.documents.associate.signer;

import com.bezzangss.sign.web.dto.response.WebResponse;
import com.bezzangss.sign.web.internal.documents.associate.signer.dto.response.SignerInternalWebResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SignerInternalWebAdapter {
    private final SignerInternalWebHandler signerInternalWebHandler;

    @PostMapping(value = "/internal/v1/signer/{id}/sign")
    public WebResponse<SignerInternalWebResponse> sign(
            @PathVariable String id
    ) {
        return signerInternalWebHandler.sign(id);
    }

    @GetMapping(value = "/internal/v1/signer/find-all-by-document-id")
    public WebResponse<List<SignerInternalWebResponse>> findAllByDocumentId(
            @RequestParam String documentId
    ) {
        return signerInternalWebHandler.findAllByDocumentId(documentId);
    }
}
