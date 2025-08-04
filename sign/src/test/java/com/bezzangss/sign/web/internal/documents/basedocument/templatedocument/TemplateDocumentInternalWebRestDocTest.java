package com.bezzangss.sign.web.internal.documents.basedocument.templatedocument;

import com.bezzangss.sign.web.internal.InternalWebRestDocTest;
import com.bezzangss.sign.web.internal.InternalWebRestDocTestConfigurer;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.dto.request.TemplateDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.resources.resource.ResourceInternalWebRestDoc;
import com.bezzangss.sign.web.internal.resources.resource.dto.request.common.ResourceInternalWebCreateByIdRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static com.bezzangss.sign.web.internal.InternalWebRestDocConstant.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {InternalWebRestDocTestConfigurer.class})
public class TemplateDocumentInternalWebRestDocTest extends InternalWebRestDocTest {
    @Test
    public void create() throws Exception {
        String resourceCreateResponseAsString = ResourceInternalWebRestDoc.create(mockMvc, httpHeaders)
                .andReturn()
                .getResponse()
                .getContentAsString();
        String resourceId = objectMapper.readTree(resourceCreateResponseAsString).path("contents").path("id").asText();
        String name = UUID.randomUUID().toString();
        String description = UUID.randomUUID().toString();

        mockMvc.perform(
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
                .andDo(
                        document("documents/basedocument/templatedocument/create",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                this.requestFieldsSnippet(),
                                super.responseFieldsSnippet(JsonFieldType.OBJECT)
                        )
                )
                .andDo(
                        document("documents/basedocument/templatedocument/create",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                this.responseFieldsSnippet()
                        )
                )
        ;
    }

    private RequestFieldsSnippet requestFieldsSnippet() {
        return requestFields(
                fieldWithPath("name").type(JsonFieldType.STRING).description(Common.REQUIRED + TemplateDocument.NAME),
                fieldWithPath("description").type(JsonFieldType.STRING).description(Common.OPTIONAL + TemplateDocument.DESCRIPTION).optional(),
                fieldWithPath("resource").type(JsonFieldType.OBJECT).description(Common.OPTIONAL + Resource.RESOURCE).optional(),
                fieldWithPath("resource.id").type(JsonFieldType.STRING).description(Common.REQUIRED + Resource.ID)
        );
    }

    private ResponseFieldsSnippet responseFieldsSnippet() {
        return responseFields(
                beneathPath("contents").withSubsectionId("contents"),
                fieldWithPath("id").type(JsonFieldType.STRING).description(TemplateDocument.ID),
                fieldWithPath("name").type(JsonFieldType.STRING).description(TemplateDocument.NAME),
                fieldWithPath("description").type(JsonFieldType.STRING).description(TemplateDocument.DESCRIPTION),
                fieldWithPath("status").type(JsonFieldType.STRING).description(TemplateDocument.STATUS),
                fieldWithPath("createdAt").type(JsonFieldType.NUMBER).description(TemplateDocument.CREATED_AT),
                fieldWithPath("lastModifiedAt").type(JsonFieldType.NUMBER).description(TemplateDocument.LAST_MODIFIED_AT).optional()
        );
    }
}
