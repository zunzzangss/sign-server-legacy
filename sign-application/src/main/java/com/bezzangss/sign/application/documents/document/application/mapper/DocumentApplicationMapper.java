package com.bezzangss.sign.application.documents.document.application.mapper;

import com.bezzangss.sign.application.documents.associate.cc.port.in.dto.request.CcApplicationCreateRequest;
import com.bezzangss.sign.application.documents.associate.cc.port.in.dto.response.CcApplicationResponse;
import com.bezzangss.sign.application.documents.associate.publisher.port.in.dto.request.PublisherApplicationCreateRequest;
import com.bezzangss.sign.application.documents.associate.publisher.port.in.dto.response.PublisherApplicationResponse;
import com.bezzangss.sign.application.documents.associate.signer.port.in.dto.request.SignerApplicationCreateRequest;
import com.bezzangss.sign.application.documents.associate.signer.port.in.dto.response.SignerApplicationResponse;
import com.bezzangss.sign.application.documents.document.port.in.dto.request.common.CcInDocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents.document.port.in.dto.request.common.PublisherInDocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents.document.port.in.dto.request.common.SignerInDocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents.document.port.in.dto.response.DocumentApplicationResponse;
import com.bezzangss.sign.application.documents.document.port.out.dto.request.DocumentRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.document.port.out.dto.request.DocumentRepositoryUpdateRequest;
import com.bezzangss.sign.application.documents.document.port.out.dto.response.DocumentRepositoryResponse;
import com.bezzangss.sign.common.mapstruct.CommonMapper;
import com.bezzangss.sign.common.mapstruct.CommonMapperConfigurer;
import com.bezzangss.sign.domain.documents.document.aggregate.Document;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfigurer.class)
public interface DocumentApplicationMapper extends CommonMapper {
    Document toDomain(DocumentRepositoryResponse documentRepositoryResponse);

    DocumentRepositoryCreateRequest toRepositoryCreateRequest(Document document);

    DocumentRepositoryUpdateRequest toRepositoryUpdateRequest(Document document);

    DocumentApplicationResponse.Publisher toApplicationResponse(PublisherApplicationResponse publisherApplicationResponse);

    DocumentApplicationResponse.Signer toApplicationResponse(SignerApplicationResponse signerApplicationResponse);

    DocumentApplicationResponse.Cc toApplicationResponse(CcApplicationResponse ccApplicationResponse);

    PublisherApplicationCreateRequest toApplicationCreateRequest(PublisherInDocumentApplicationCreateRequest publisherInDocumentApplicationCreateRequest, String documentId);

    SignerApplicationCreateRequest toApplicationCreateRequest(SignerInDocumentApplicationCreateRequest signerInDocumentApplicationCreateRequest, String documentId);

    CcApplicationCreateRequest toApplicationCreateRequest(CcInDocumentApplicationCreateRequest ccInDocumentApplicationCreateRequest, String documentId);
}
