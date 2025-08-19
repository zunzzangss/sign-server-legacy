package com.bezzangss.sign.web.internal.documents.associate.signer.dto.response;

import lombok.*;

import java.time.Instant;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Setter
@Getter
public class SignerInternalWebResponse {
    private String id;
    private String name;
    private String email;
    private String cellPhone;
    private int order;
    private String status;
    private String documentId;
    private Instant createdAt;
    private Instant lastModifiedAt;
}
