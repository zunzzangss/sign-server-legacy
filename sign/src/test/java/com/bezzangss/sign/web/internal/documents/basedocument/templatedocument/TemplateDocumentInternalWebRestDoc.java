package com.bezzangss.sign.web.internal.documents.basedocument.templatedocument;

import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.dto.request.TemplateDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.resources.resource.ResourceInternalWebRestDoc;
import com.bezzangss.sign.web.internal.resources.resource.dto.request.common.ResourceInternalWebCreateByIdRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TemplateDocumentInternalWebRestDoc {
    public static ResultActions create(MockMvc mockMvc, HttpHeaders httpHeaders, ObjectMapper objectMapper) throws Exception {
        String resourceCreateResponseAsString = ResourceInternalWebRestDoc.create(mockMvc, httpHeaders)
                .andReturn()
                .getResponse()
                .getContentAsString();
        String resourceId = objectMapper.readTree(resourceCreateResponseAsString).path("contents").path("id").asText();
        String name = UUID.randomUUID().toString();
        String description = UUID.randomUUID().toString();

        return mockMvc.perform(
                        post("/internal/v1/template-document/create")
                                .headers(httpHeaders)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(
                                                TemplateDocumentInternalWebCreateRequest.builder()
                                                        .name(name)
                                                        .description(description)
                                                        .resource(
                                                                ResourceInternalWebCreateByIdRequest.builder()
                                                                        .id(resourceId)
                                                                        .build()
                                                        )
                                                        .build()
                                        )
                                )
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contents.id").isNotEmpty())
                .andExpect(jsonPath("$.contents.name").value(name))
                .andExpect(jsonPath("$.contents.description").value(description))
                .andExpect(jsonPath("$.contents.status").value("NONE"))
                .andExpect(jsonPath("$.contents.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.contents.lastModifiedAt").isEmpty())
                .andExpect(jsonPath("$.contents.id").isNotEmpty())
        ;
    }
}
