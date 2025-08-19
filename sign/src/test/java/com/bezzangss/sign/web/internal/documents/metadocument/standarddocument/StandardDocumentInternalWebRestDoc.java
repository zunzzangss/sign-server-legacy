package com.bezzangss.sign.web.internal.documents.metadocument.standarddocument;

import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.TemplateDocumentInternalWebRestDoc;
import com.bezzangss.sign.web.internal.documents.document.dto.request.CcInDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.document.dto.request.PublisherInDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.document.dto.request.SignerInDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument._standarddocument.dto.request.StandardDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument.dto.request.BaseDocumentInMetaDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument.dto.request.DocumentInMetaDocumentInternalWebCreateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.UUID;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StandardDocumentInternalWebRestDoc {
    public static ResultActions createSuccess(MockMvc mockMvc, HttpHeaders httpHeaders, ObjectMapper objectMapper) throws Exception {
        String templateDocumentCreateResponseAsString = TemplateDocumentInternalWebRestDoc.createSuccess(mockMvc, httpHeaders, objectMapper)
                .andReturn()
                .getResponse()
                .getContentAsString();
        String templateDocumentId = objectMapper.readTree(templateDocumentCreateResponseAsString).path("contents").path("id").asText();
        StandardDocumentInternalWebCreateRequest standardDocumentInternalWebCreateRequest = getSuccessRequest(templateDocumentId);

        return create(mockMvc, httpHeaders, objectMapper, standardDocumentInternalWebCreateRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contents.id").isNotEmpty())
                .andExpect(jsonPath("$.contents.name").value(standardDocumentInternalWebCreateRequest.getName()))
                .andExpect(jsonPath("$.contents.description").value(standardDocumentInternalWebCreateRequest.getDescription()))
                .andExpect(jsonPath("$.contents.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.contents.lastModifiedAt").isEmpty())
                .andExpect(jsonPath("$.contents.document").isNotEmpty())
                .andExpect(jsonPath("$.contents.document.id").isNotEmpty());
    }

    public static ResultActions create(MockMvc mockMvc, HttpHeaders httpHeaders, ObjectMapper objectMapper, StandardDocumentInternalWebCreateRequest standardDocumentInternalWebCreateRequest) throws Exception {
        return mockMvc.perform(
                        post("/internal/v1/standard-document/create")
                                .headers(httpHeaders)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(standardDocumentInternalWebCreateRequest))
                )
                .andDo(print());
    }

    public static ResultActions findByIdSuccess(MockMvc mockMvc, HttpHeaders httpHeaders, ObjectMapper objectMapper) throws Exception {
        String templateDocumentCreateResponseAsString = TemplateDocumentInternalWebRestDoc.createSuccess(mockMvc, httpHeaders, objectMapper)
                .andReturn()
                .getResponse()
                .getContentAsString();
        String templateDocumentId = objectMapper.readTree(templateDocumentCreateResponseAsString).path("contents").path("id").asText();
        StandardDocumentInternalWebCreateRequest standardDocumentInternalWebCreateRequest = getSuccessRequest(templateDocumentId);

        String standardDocumentCreateResponseAsString = create(mockMvc, httpHeaders, objectMapper, standardDocumentInternalWebCreateRequest)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        String id = objectMapper.readTree(standardDocumentCreateResponseAsString).path("contents").path("id").asText();

        return findById(mockMvc, httpHeaders, id)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contents.id").value(id))
                .andExpect(jsonPath("$.contents.name").value(standardDocumentInternalWebCreateRequest.getName()))
                .andExpect(jsonPath("$.contents.description").value(standardDocumentInternalWebCreateRequest.getDescription()))
                .andExpect(jsonPath("$.contents.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.contents.lastModifiedAt").isEmpty());
    }

    public static ResultActions findById(MockMvc mockMvc, HttpHeaders httpHeaders, String id) throws Exception {
        return mockMvc.perform(
                        get("/internal/v1/standard-document/" + id)
                                .param("include", "DOCUMENT")
                                .headers(httpHeaders)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print());
    }

    private static StandardDocumentInternalWebCreateRequest getSuccessRequest(String templateDocumentId) {
        return StandardDocumentInternalWebCreateRequest.builder()
                .name(UUID.randomUUID().toString())
                .description(UUID.randomUUID().toString())
                .baseDocument(
                        BaseDocumentInMetaDocumentInternalWebCreateRequest.builder()
                                .id(templateDocumentId)
                                .type("TEMPLATE_DOCUMENT")
                                .build()
                )
                .document(
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
                )
                .build();
    }
}
