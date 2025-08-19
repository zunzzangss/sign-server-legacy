package com.bezzangss.sign.web.internal.documents.document;

import com.bezzangss.sign.web.internal.documents.metadocument.standarddocument.StandardDocumentInternalWebRestDoc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DocumentInternalWebRestDoc {
    public static ResultActions findByIdSuccess(MockMvc mockMvc, HttpHeaders httpHeaders, ObjectMapper objectMapper) throws Exception {
        String standardDocumentCreateResponseAsString = StandardDocumentInternalWebRestDoc.createSuccess(mockMvc, httpHeaders, objectMapper)
                .andReturn()
                .getResponse()
                .getContentAsString();
        String standardDocumentId = objectMapper.readTree(standardDocumentCreateResponseAsString).path("contents").path("id").asText();
        String documentId = objectMapper.readTree(standardDocumentCreateResponseAsString).path("contents").path("document").path("id").asText();

        return findById(mockMvc, httpHeaders, documentId)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contents.id").isNotEmpty())
                .andExpect(jsonPath("$.contents.name").isNotEmpty())
                .andExpect(jsonPath("$.contents.description").isNotEmpty())
                .andExpect(jsonPath("$.contents.status").value("PROCESSING"))
                .andExpect(jsonPath("$.contents.metaDocumentType").value("STANDARD_DOCUMENT"))
                .andExpect(jsonPath("$.contents.metaDocumentId").value(standardDocumentId))
                .andExpect(jsonPath("$.contents.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.contents.lastModifiedAt").isNotEmpty())
                .andExpect(jsonPath("$.contents.publisher").isNotEmpty())
                .andExpect(jsonPath("$.contents.publisher.id").isNotEmpty())
                .andExpect(jsonPath("$.contents.signers").isNotEmpty())
                .andExpect(jsonPath("$.contents.signers[*].id").isNotEmpty())
                .andExpect(jsonPath("$.contents.ccs").isNotEmpty())
                .andExpect(jsonPath("$.contents.ccs[*].id").isNotEmpty());
    }

    public static ResultActions findById(MockMvc mockMvc, HttpHeaders httpHeaders, String id) throws Exception {
        return mockMvc.perform(
                        get("/internal/v1/document/" + id)
                                .param("include", "PUBLISHER", "SIGNER", "CC")
                                .headers(httpHeaders)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print());
    }
}
