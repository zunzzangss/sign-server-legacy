package com.bezzangss.sign.domain.documents.associate.signer.service.common;

import com.bezzangss.sign.domain.DomainException;
import com.bezzangss.sign.domain.documents.associate.signer.aggregate.Signer;
import com.bezzangss.sign.domain.documents.document.aggregate.Document;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.bezzangss.sign.common.exception.ErrorCode.*;

@Component
public class SignerDomainCommonService {
    public void validateContains(Signer signer, Document document) {
        if (!signer.getDocumentId().equals(document.getId())) throw new DomainException(SIGNER_ILLEGAL_INTERNAL_SERVER_ERROR, signer.getId());
    }

    public void validateContains(List<Signer> signers, Document document) {
        if (!signers.stream().allMatch(signer -> signer.getDocumentId().equals(document.getId()))) {
            throw new DomainException(
                    SIGNER_ILLEGAL_INTERNAL_SERVER_ERROR,
                    signers.stream()
                            .filter(signer -> !signer.getDocumentId().equals(document.getId()))
                            .map(Signer::getId)
                            .collect(Collectors.joining(" "))
            );
        }
    }

    public void validateAllMatchNone(List<Signer> signers) {
        if (!signers.stream().allMatch(Signer::isNone)) {
            throw new DomainException(
                    SIGNER_STATUS_IS_NOT_NONE_EXCEPTION,
                    signers.stream()
                            .filter(signer -> !signer.isNone())
                            .map(Signer::getId)
                            .collect(Collectors.joining(" "))
            );
        }
    }

    public void validateAllMatchSigned(List<Signer> signers) {
        if (!signers.stream().allMatch(Signer::isSigned)) {
            throw new DomainException(
                    SIGNER_STATUS_IS_NOT_SIGNED_EXCEPTION,
                    signers.stream()
                            .filter(signer -> !signer.isSigned())
                            .map(Signer::getId)
                            .collect(Collectors.joining(" "))
            );
        }
    }

    public void validateOrderByReady(Signer signer, List<Signer> signers) {
        this.findAnyOrderByReady(signers)
                .filter(orderByReady -> orderByReady == signer.getOrder())
                .orElseThrow(() -> new DomainException(SIGNER_IS_NOT_IN_ORDER_EXCEPTION, signer.getId()));
    }

    public List<Signer> filterByMinOrder(List<Signer> signers) {
        return this.filterByOrder(signers, this.getMinOrder(signers));
    }

    public List<Signer> filterByOrder(List<Signer> signers, int order) {
        return signers.stream()
                .filter(signer -> signer.getOrder() == order)
                .collect(Collectors.toList());
    }

    public int getMinOrder(List<Signer> signers) {
        return signers.stream()
                .mapToInt(Signer::getOrder)
                .min()
                .orElseThrow(() -> new DomainException(
                                SIGNER_ILLEGAL_INTERNAL_SERVER_ERROR,
                                signers.stream()
                                        .map(Signer::getId)
                                        .collect(Collectors.joining(" "))
                        )
                );
    }

    public Optional<Integer> findAnyOrderByReady(List<Signer> signers) {
        return signers.stream()
                .filter(Signer::isReady)
                .map(Signer::getOrder)
                .findAny();
    }
}
