package com.bezzangss.sign.web.internal.documents.metadocument.standarddocument;

import com.bezzangss.sign.web.internal.InternalWebRestDocConstant.*;
import com.bezzangss.sign.web.internal.InternalWebRestDocTest;
import com.bezzangss.sign.web.internal.InternalWebRestDocTestConfigurer;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.TemplateDocumentInternalWebRestDoc;
import com.bezzangss.sign.web.internal.documents.document.dto.request.CcInDocumentWebInternalCreateRequest;
import com.bezzangss.sign.web.internal.documents.document.dto.request.PublisherInDocumentWebInternalCreateRequest;
import com.bezzangss.sign.web.internal.documents.document.dto.request.SignerInDocumentWebInternalCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument._standarddocument.dto.request.StandardDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument.dto.request.BaseDocumentInMetaDocumentWebInternalCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument.dto.request.DocumentInMetaDocumentWebInternalCreateRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.UUID;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {InternalWebRestDocTestConfigurer.class})
public class StandardDocumentInternalWebRestDocTest extends InternalWebRestDocTest {
    @Test
    public void create() throws Exception {
        String templateDocumentCreateResponseAsString = TemplateDocumentInternalWebRestDoc.create(mockMvc, httpHeaders, objectMapper)
                .andReturn()
                .getResponse()
                .getContentAsString();
        String templateDocumentId = objectMapper.readTree(templateDocumentCreateResponseAsString).path("contents").path("id").asText();
        String name = UUID.randomUUID().toString();
        String description = UUID.randomUUID().toString();

        mockMvc.perform(
                        post("/internal/v1/standard-document/create")
                                .headers(httpHeaders)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(
                                                StandardDocumentInternalWebCreateRequest.builder()
                                                        .name(name)
                                                        .description(description)
                                                        .baseDocument(
                                                                BaseDocumentInMetaDocumentWebInternalCreateRequest.builder()
                                                                        .id(templateDocumentId)
                                                                        .type("TEMPLATE_DOCUMENT")
                                                                        .build()
                                                        )
                                                        .document(
                                                                DocumentInMetaDocumentWebInternalCreateRequest.builder()
                                                                        .name(name)
                                                                        .description(description)
                                                                        .publisher(
                                                                                PublisherInDocumentWebInternalCreateRequest.builder()
                                                                                        .name("publisher")
                                                                                        .email("publisher@bezzangss.com")
                                                                                        .cellPhone("01000000000")
                                                                                        .build()
                                                                        )
                                                                        .signers(
                                                                                Arrays.asList(
                                                                                        SignerInDocumentWebInternalCreateRequest.builder()
                                                                                                .name("signer1")
                                                                                                .email("signer1@bezzangss.com")
                                                                                                .cellPhone("01011111111")
                                                                                                .order(0)
                                                                                                .build()
                                                                                        , SignerInDocumentWebInternalCreateRequest.builder()
                                                                                                .name("signer2")
                                                                                                .email("signer2@bezzangss.com")
                                                                                                .cellPhone("01022222222")
                                                                                                .order(0)
                                                                                                .build()
                                                                                )
                                                                        )
                                                                        .ccs(
                                                                                Arrays.asList(
                                                                                        CcInDocumentWebInternalCreateRequest.builder()
                                                                                                .name("cc1")
                                                                                                .email("cc1@bezzangss.com")
                                                                                                .cellPhone("01033333333")
                                                                                                .build()
                                                                                        , CcInDocumentWebInternalCreateRequest.builder()
                                                                                                .name("cc2")
                                                                                                .email("cc2@bezzangss.com")
                                                                                                .cellPhone("01044444444")
                                                                                                .build()
                                                                                )
                                                                        )
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
//                .andExpect(jsonPath("$.contents.status").value("NONE"))
                .andExpect(jsonPath("$.contents.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.contents.lastModifiedAt").isEmpty())
                .andDo(
                        document("documents/metadocument/standarddocument/create",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                this.requestFieldsSnippet(),
                                super.responseFieldsSnippet(JsonFieldType.OBJECT)
                        )
                )
                .andDo(
                        document("documents/metadocument/standarddocument/create",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                this.responseFieldsSnippet()
                        )
                )
        ;
    }

    private RequestFieldsSnippet requestFieldsSnippet() {
        return requestFields(
                fieldWithPath("name").type(JsonFieldType.STRING).description(Common.REQUIRED + StandardDocument.NAME),
                fieldWithPath("description").type(JsonFieldType.STRING).description(Common.OPTIONAL + StandardDocument.DESCRIPTION).optional(),
                fieldWithPath("baseDocument").type(JsonFieldType.OBJECT).description(Common.REQUIRED + BaseDocument.BASE_DOCUMENT),
                fieldWithPath("baseDocument.id").type(JsonFieldType.STRING).description(Common.REQUIRED + BaseDocument.ID),
                fieldWithPath("baseDocument.type").type(JsonFieldType.STRING).description(Common.REQUIRED + BaseDocument.TYPE),
                fieldWithPath("document").type(JsonFieldType.OBJECT).description(Common.REQUIRED + Document.DOCUMENT),
                fieldWithPath("document.name").type(JsonFieldType.STRING).description(Common.REQUIRED + Document.NAME),
                fieldWithPath("document.description").type(JsonFieldType.STRING).description(Common.OPTIONAL + Document.DESCRIPTION).optional(),

                fieldWithPath("document.publisher").type(JsonFieldType.OBJECT).description(Common.REQUIRED + Publisher.PUBLISHER),
                fieldWithPath("document.publisher.name").type(JsonFieldType.STRING).description(Common.REQUIRED + Publisher.NAME),
                fieldWithPath("document.publisher.email").type(JsonFieldType.STRING).description(Common.OPTIONAL + Publisher.EMAIL),
                fieldWithPath("document.publisher.cellPhone").type(JsonFieldType.STRING).description(Common.OPTIONAL + Publisher.CELL_PHONE),

                fieldWithPath("document.signers").type(JsonFieldType.ARRAY).description(Common.REQUIRED + Signer.SIGNER),
                fieldWithPath("document.signers[].name").type(JsonFieldType.STRING).description(Common.REQUIRED + Signer.NAME),
                fieldWithPath("document.signers[].email").type(JsonFieldType.STRING).description(Common.OPTIONAL + Signer.EMAIL),
                fieldWithPath("document.signers[].cellPhone").type(JsonFieldType.STRING).description(Common.OPTIONAL + Signer.CELL_PHONE),
                fieldWithPath("document.signers[].order").type(JsonFieldType.NUMBER).description(Common.OPTIONAL + Signer.ORDER),

                fieldWithPath("document.ccs").type(JsonFieldType.ARRAY).description(Common.OPTIONAL + Cc.CC),
                fieldWithPath("document.ccs[].name").type(JsonFieldType.STRING).description(Common.REQUIRED + Cc.NAME),
                fieldWithPath("document.ccs[].email").type(JsonFieldType.STRING).description(Common.OPTIONAL + Cc.EMAIL),
                fieldWithPath("document.ccs[].cellPhone").type(JsonFieldType.STRING).description(Common.OPTIONAL + Cc.CELL_PHONE)
        );
    }

    private ResponseFieldsSnippet responseFieldsSnippet() {
        return responseFields(
                beneathPath("contents").withSubsectionId("contents"),
                fieldWithPath("id").type(JsonFieldType.STRING).description(StandardDocument.ID),
                fieldWithPath("name").type(JsonFieldType.STRING).description(StandardDocument.NAME),
                fieldWithPath("description").type(JsonFieldType.STRING).description(StandardDocument.DESCRIPTION),
                fieldWithPath("createdAt").type(JsonFieldType.NUMBER).description(StandardDocument.CREATED_AT),
                fieldWithPath("lastModifiedAt").type(JsonFieldType.NUMBER).description(StandardDocument.LAST_MODIFIED_AT).optional()
        );
    }
}
