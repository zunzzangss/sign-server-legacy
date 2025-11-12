package com.bezzangss.sign.domain.documents.document.aggregate;

import com.bezzangss.sign.domain.documents.document.dto.DocumentDomainCreateRequest;
import com.bezzangss.sign.domain.documents.metadocument.MetaDocumentType;
import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;
import java.util.UUID;

public class DocumentTest {
    @Test
    public void 문서_생성_성공() {
        // given
        DocumentDomainCreateRequest documentDomainCreateRequest = DocumentDomainCreateRequest.builder()
                .id(UUID.randomUUID().toString())
                .name(UUID.randomUUID().toString())
                .description(UUID.randomUUID().toString())
                .metaDocumentType(MetaDocumentType.STANDARD_DOCUMENT)
                .metaDocumentId(UUID.randomUUID().toString())
                .createdAt(Instant.now())
                .lastModifiedAt(Instant.now())
                .build();

        // when
        Document document = Document.create(documentDomainCreateRequest);

        // then
        Assert.assertTrue(document.isNone());
    }
}
