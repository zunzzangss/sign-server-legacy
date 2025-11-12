package com.bezzangss.sign.domain.documents.document.service;

import com.bezzangss.sign.domain.DomainException;
import com.bezzangss.sign.domain.documents.associate.signer.aggregate.Signer;
import com.bezzangss.sign.domain.documents.associate.signer.dto.SignerDomainCreateRequest;
import com.bezzangss.sign.domain.documents.associate.signer.service.common.SignerDomainCommonService;
import com.bezzangss.sign.domain.documents.document.DocumentStatus;
import com.bezzangss.sign.domain.documents.document.aggregate.Document;
import com.bezzangss.sign.domain.documents.document.dto.DocumentDomainCreateRequest;
import com.bezzangss.sign.domain.documents.document.event.DocumentDomainEvent;
import com.bezzangss.sign.domain.documents.metadocument.MetaDocumentType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.bezzangss.sign.common.exception.ErrorCode.SIGNER_ILLEGAL_INTERNAL_SERVER_ERROR;
import static com.bezzangss.sign.common.exception.ErrorCode.SIGNER_STATUS_IS_NOT_SIGNED_EXCEPTION;

public class DocumentDomainServiceTest {
    private static DocumentDomainService documentDomainService;

    private Document document;
    private List<Signer> signers;

    @BeforeClass
    public static void beforeClass() {
        documentDomainService = new DocumentDomainService(new SignerDomainCommonService());
    }

    @Before
    public void before() {
        document = Document.create(
                DocumentDomainCreateRequest.builder()
                        .name(UUID.randomUUID().toString())
                        .metaDocumentType(MetaDocumentType.STANDARD_DOCUMENT)
                        .metaDocumentId(UUID.randomUUID().toString())
                        .build()
        );

        signers = Arrays.asList(
                Signer.create(
                        SignerDomainCreateRequest.builder()
                                .name(UUID.randomUUID().toString())
                                .order(0)
                                .documentId(document.getId())
                                .build()
                )
                , Signer.create(
                        SignerDomainCreateRequest.builder()
                                .name(UUID.randomUUID().toString())
                                .order(1)
                                .documentId(document.getId())
                                .build()
                )
        );
    }

    @Test
    public void 문서_진행_성공() {
        // given

        // when
        ApplicationEvent event = documentDomainService.process(signers, document);

        // then
        Assert.assertTrue(document.isProcessing());
        Assert.assertNotNull(event);
        Assert.assertTrue(event instanceof DocumentDomainEvent);
        Assert.assertEquals(document.getId(), ((DocumentDomainEvent) event).getId());
        Assert.assertSame(DocumentStatus.PROCESSING, ((DocumentDomainEvent) event).getStatus());
    }

    @Test
    public void 문서_진행_실패_서명자_문서_불일치() {
        // given
        signers = Arrays.asList(
                Signer.create(
                        SignerDomainCreateRequest.builder()
                                .name(UUID.randomUUID().toString())
                                .order(0)
                                .documentId(UUID.randomUUID().toString())
                                .build()
                )
                , Signer.create(
                        SignerDomainCreateRequest.builder()
                                .name(UUID.randomUUID().toString())
                                .order(1)
                                .documentId(UUID.randomUUID().toString())
                                .build()
                )
        );

        // when
        DomainException domainException = Assert.assertThrows(DomainException.class, () -> documentDomainService.process(signers, document));

        // then
        Assert.assertSame(SIGNER_ILLEGAL_INTERNAL_SERVER_ERROR, domainException.getErrorCode());
    }

    @Test
    public void 문서_완료_실패_서명자_미서명() {
        // given
        documentDomainService.process(signers, document);

        // when
        DomainException domainException = Assert.assertThrows(DomainException.class, () -> documentDomainService.complete(signers, document));

        // then
        Assert.assertSame(SIGNER_STATUS_IS_NOT_SIGNED_EXCEPTION, domainException.getErrorCode());
    }

    @Test
    public void 문서_완료_성공() {
        // given
        documentDomainService.process(signers, document);

        for (Signer signer : signers) {
            signer.waits();
            signer.ready();
            signer.sign();
        }

        // when
        documentDomainService.complete(signers, document);

        // then
        Assert.assertTrue(document.isCompleted());
    }
}
