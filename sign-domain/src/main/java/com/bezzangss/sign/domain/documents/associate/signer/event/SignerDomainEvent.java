package com.bezzangss.sign.domain.documents.associate.signer.event;

import com.bezzangss.sign.domain.DomainException;
import com.bezzangss.sign.domain.documents.associate.signer.SignerStatus;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@Getter
public class SignerDomainEvent {
    private final String id;
    private final SignerStatus status;

    @Builder
    private SignerDomainEvent(String id, SignerStatus status) {
        this.id = id;
        this.status = status;
        this.validate();
    }

    public static SignerDomainEvent waits(String id) {
        return SignerDomainEvent.builder()
                .id(id)
                .status(SignerStatus.WAITING)
                .build();
    }

    public static SignerDomainEvent ready(String id) {
        return SignerDomainEvent.builder()
                .id(id)
                .status(SignerStatus.READY)
                .build();
    }

    private void validate() {
        if (ObjectUtils.isEmpty(this.id)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");
        if (ObjectUtils.isEmpty(this.status)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "status");
    }
}
