package com.bezzangss.sign.web.internal.documents.metadocument.groupdocument;

import com.bezzangss.sign.web.internal.documents.document.dto.request.CcInDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.document.dto.request.PublisherInDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.document.dto.request.SignerInDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument._groupdocument.dto.request.GroupDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument.dto.request.BaseDocumentInMetaDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument.dto.request.DocumentInMetaDocumentInternalWebCreateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.UUID;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class GroupDocumentInternalWebRestDoc {
    public static ResultActions create(MockMvc mockMvc, HttpHeaders httpHeaders, ObjectMapper objectMapper, GroupDocumentInternalWebCreateRequest groupDocumentInternalWebCreateRequest) throws Exception {
        return mockMvc.perform(
                        post("/internal/v1/group-document/create")
                                .headers(httpHeaders)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(groupDocumentInternalWebCreateRequest))
                )
                .andDo(print());
    }

    public static ResultActions findById(MockMvc mockMvc, HttpHeaders httpHeaders, String id, MultiValueMap<String, String> params) throws Exception {
        return mockMvc.perform(
                        get("/internal/v1/group-document/{id}", id)
                                .params(params)
                                .headers(httpHeaders)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print());
    }

    public static GroupDocumentInternalWebCreateRequest getCreateRequestSuccess(String templateDocumentId) {
        return GroupDocumentInternalWebCreateRequest.builder()
                .name(UUID.randomUUID().toString())
                .description(UUID.randomUUID().toString())
                .baseDocument(
                        BaseDocumentInMetaDocumentInternalWebCreateRequest.builder()
                                .id(templateDocumentId)
                                .type("TEMPLATE_DOCUMENT")
                                .build()
                )
                .documents(
                        Arrays.asList(
                                DocumentInMetaDocumentInternalWebCreateRequest.builder()
                                        .name(UUID.randomUUID().toString())
                                        .description(UUID.randomUUID().toString())
                                        .publisher(
                                                PublisherInDocumentInternalWebCreateRequest.builder()
                                                        .name("publisher")
                                                        .email("publisher@bezzangss.com")
                                                        .cellPhone("01000000000")
                                                        .build()
                                        )
                                        .signers(
                                                Arrays.asList(
                                                        SignerInDocumentInternalWebCreateRequest.builder()
                                                                .name("signer1")
                                                                .email("signer1@bezzangss.com")
                                                                .cellPhone("01011111111")
                                                                .order(0)
                                                                .build()
                                                        , SignerInDocumentInternalWebCreateRequest.builder()
                                                                .name("signer2")
                                                                .email("signer2@bezzangss.com")
                                                                .cellPhone("01022222222")
                                                                .order(1)
                                                                .build()
                                                        , SignerInDocumentInternalWebCreateRequest.builder()
                                                                .name("signer3")
                                                                .email("signer3@bezzangss.com")
                                                                .cellPhone("01033333333")
                                                                .order(2)
                                                                .build()
                                                        , SignerInDocumentInternalWebCreateRequest.builder()
                                                                .name("signer4")
                                                                .email("signer4@bezzangss.com")
                                                                .cellPhone("01044444444")
                                                                .order(2)
                                                                .build()
                                                        , SignerInDocumentInternalWebCreateRequest.builder()
                                                                .name("signer5")
                                                                .email("signer5@bezzangss.com")
                                                                .cellPhone("01055555555")
                                                                .order(3)
                                                                .build()
                                                )
                                        )
                                        .ccs(
                                                Arrays.asList(
                                                        CcInDocumentInternalWebCreateRequest.builder()
                                                                .name("cc1")
                                                                .email("cc1@bezzangss.com")
                                                                .cellPhone("01033333333")
                                                                .build()
                                                        , CcInDocumentInternalWebCreateRequest.builder()
                                                                .name("cc2")
                                                                .email("cc2@bezzangss.com")
                                                                .cellPhone("01044444444")
                                                                .build()
                                                )
                                        )
                                        .build()
                                , DocumentInMetaDocumentInternalWebCreateRequest.builder()
                                        .name(UUID.randomUUID().toString())
                                        .description(UUID.randomUUID().toString())
                                        .publisher(
                                                PublisherInDocumentInternalWebCreateRequest.builder()
                                                        .name("publisher")
                                                        .email("publisher@bezzangss.com")
                                                        .cellPhone("01000000000")
                                                        .build()
                                        )
                                        .signers(
                                                Arrays.asList(
                                                        SignerInDocumentInternalWebCreateRequest.builder()
                                                                .name("signer1")
                                                                .email("signer1@bezzangss.com")
                                                                .cellPhone("01011111111")
                                                                .order(0)
                                                                .build()
                                                        , SignerInDocumentInternalWebCreateRequest.builder()
                                                                .name("signer2")
                                                                .email("signer2@bezzangss.com")
                                                                .cellPhone("01022222222")
                                                                .order(1)
                                                                .build()
                                                        , SignerInDocumentInternalWebCreateRequest.builder()
                                                                .name("signer3")
                                                                .email("signer3@bezzangss.com")
                                                                .cellPhone("01033333333")
                                                                .order(2)
                                                                .build()
                                                        , SignerInDocumentInternalWebCreateRequest.builder()
                                                                .name("signer4")
                                                                .email("signer4@bezzangss.com")
                                                                .cellPhone("01044444444")
                                                                .order(2)
                                                                .build()
                                                        , SignerInDocumentInternalWebCreateRequest.builder()
                                                                .name("signer5")
                                                                .email("signer5@bezzangss.com")
                                                                .cellPhone("01055555555")
                                                                .order(3)
                                                                .build()
                                                )
                                        )
                                        .ccs(
                                                Arrays.asList(
                                                        CcInDocumentInternalWebCreateRequest.builder()
                                                                .name("cc1")
                                                                .email("cc1@bezzangss.com")
                                                                .cellPhone("01033333333")
                                                                .build()
                                                        , CcInDocumentInternalWebCreateRequest.builder()
                                                                .name("cc2")
                                                                .email("cc2@bezzangss.com")
                                                                .cellPhone("01044444444")
                                                                .build()
                                                )
                                        )
                                        .build()
                        )
                )
                .build();
    }
}
