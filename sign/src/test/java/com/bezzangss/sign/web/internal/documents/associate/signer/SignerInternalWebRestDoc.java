package com.bezzangss.sign.web.internal.documents.associate.signer;

import com.bezzangss.sign.web.internal.documents.associate.signer.dto.response.SignerInternalWebResponse;
import com.bezzangss.sign.web.internal.documents.document.DocumentInternalWebRestDoc;
import com.bezzangss.sign.web.internal.documents.metadocument.standarddocument.StandardDocumentInternalWebRestDoc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SignerInternalWebRestDoc {
    public static ResultActions signSuccess(MockMvc mockMvc, HttpHeaders httpHeaders, ObjectMapper objectMapper) throws Exception {
        String standardDocumentCreateResponseAsString = StandardDocumentInternalWebRestDoc.createSuccess(mockMvc, httpHeaders, objectMapper)
                .andReturn()
                .getResponse()
                .getContentAsString();
        String documentId = objectMapper.readTree(standardDocumentCreateResponseAsString).path("contents").path("document").path("id").asText();

        String signerResponseAsString = findAllByDocumentId(mockMvc, httpHeaders, documentId)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<SignerInternalWebResponse> signerInternalWebResponses = objectMapper.readValue(
                objectMapper.readTree(signerResponseAsString).path("contents").toString(),
                objectMapper.getTypeFactory().constructType(
                        new ParameterizedTypeReference<List<SignerInternalWebResponse>>() {
                        }.getType()
                )
        );

        String id = signerInternalWebResponses.stream()
                .sorted(Comparator.comparing(SignerInternalWebResponse::getOrder))
                .map(SignerInternalWebResponse::getId)
                .findAny()
                .orElseThrow(() -> new Exception("Signer not found"));

        return sign(mockMvc, httpHeaders, id);
    }

    public static void signAllSuccess(MockMvc mockMvc, HttpHeaders httpHeaders, ObjectMapper objectMapper) throws Exception {
        String standardDocumentCreateResponseAsString = StandardDocumentInternalWebRestDoc.createSuccess(mockMvc, httpHeaders, objectMapper)
                .andReturn()
                .getResponse()
                .getContentAsString();
        String documentId = objectMapper.readTree(standardDocumentCreateResponseAsString).path("contents").path("document").path("id").asText();

        String signerResponseAsString = findAllByDocumentId(mockMvc, httpHeaders, documentId)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<SignerInternalWebResponse> signerInternalWebResponses = objectMapper.readValue(
                objectMapper.readTree(signerResponseAsString).path("contents").toString(),
                objectMapper.getTypeFactory().constructType(
                        new ParameterizedTypeReference<List<SignerInternalWebResponse>>() {
                        }.getType()
                )
        );

        List<String> ids = signerInternalWebResponses.stream()
                .sorted(Comparator.comparing(SignerInternalWebResponse::getOrder))
                .map(SignerInternalWebResponse::getId)
                .collect(Collectors.toList());

        for (String id : ids) {
            sign(mockMvc, httpHeaders, id);
        }

        DocumentInternalWebRestDoc.findById(mockMvc, httpHeaders, documentId)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contents.status").value("COMPLETED"));
    }

    public static ResultActions findAllByDocumentIdSuccess(MockMvc mockMvc, HttpHeaders httpHeaders, ObjectMapper objectMapper) throws Exception {
        String standardDocumentCreateResponseAsString = StandardDocumentInternalWebRestDoc.createSuccess(mockMvc, httpHeaders, objectMapper)
                .andReturn()
                .getResponse()
                .getContentAsString();
        String documentId = objectMapper.readTree(standardDocumentCreateResponseAsString).path("contents").path("document").path("id").asText();

        return findAllByDocumentId(mockMvc, httpHeaders, documentId)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contents[*].id").isNotEmpty())
                .andExpect(jsonPath("$.contents[*].name").isNotEmpty())
                .andExpect(jsonPath("$.contents[*].email").isNotEmpty())
                .andExpect(jsonPath("$.contents[*].cellPhone").isNotEmpty())
                .andExpect(jsonPath("$.contents[*].order").isNotEmpty())
                .andExpect(jsonPath("$.contents[*].status").isNotEmpty())
                .andExpect(jsonPath("$.contents[*].documentId").isNotEmpty())
                .andExpect(jsonPath("$.contents[*].createdAt").isNotEmpty())
                .andExpect(jsonPath("$.contents[*].lastModifiedAt").isNotEmpty());
    }

    public static ResultActions sign(MockMvc mockMvc, HttpHeaders httpHeaders, String id) throws Exception {
        return mockMvc.perform(
                        post("/internal/v1/signer/{id}/sign", id)
                                .headers(httpHeaders)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print());
    }

    private static ResultActions findAllByDocumentId(MockMvc mockMvc, HttpHeaders httpHeaders, String documentId) throws Exception {
        return mockMvc.perform(
                        get("/internal/v1/signer/list-by-document-id")
                                .param("documentId", documentId)
                                .headers(httpHeaders)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print());
    }
}
