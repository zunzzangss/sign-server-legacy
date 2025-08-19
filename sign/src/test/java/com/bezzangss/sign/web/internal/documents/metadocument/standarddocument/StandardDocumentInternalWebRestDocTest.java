package com.bezzangss.sign.web.internal.documents.metadocument.standarddocument;

import com.bezzangss.sign.web.internal.InternalWebRestDocConstant.*;
import com.bezzangss.sign.web.internal.InternalWebRestDocTest;
import com.bezzangss.sign.web.internal.InternalWebRestDocTestConfigurer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {InternalWebRestDocTestConfigurer.class})
public class StandardDocumentInternalWebRestDocTest extends InternalWebRestDocTest {
    @Test
    public void createSuccess() throws Exception {
        StandardDocumentInternalWebRestDoc.createSuccess(mockMvc, httpHeaders, objectMapper)
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
                );
    }

    @Test
    public void findByIdSuccess() throws Exception {
        StandardDocumentInternalWebRestDoc.findByIdSuccess(mockMvc, httpHeaders, objectMapper)
                .andDo(
                        document("documents/metadocument/standarddocument/find-by-id",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                this.responseFieldsSnippet()
                        )
                );
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
                fieldWithPath("description").type(JsonFieldType.STRING).description(StandardDocument.DESCRIPTION).optional(),
                fieldWithPath("createdAt").type(JsonFieldType.NUMBER).description(StandardDocument.CREATED_AT),
                fieldWithPath("lastModifiedAt").type(JsonFieldType.NUMBER).description(StandardDocument.LAST_MODIFIED_AT).optional(),
                fieldWithPath("document").type(JsonFieldType.OBJECT).description(Document.DOCUMENT).optional(),
                fieldWithPath("document.id").type(JsonFieldType.STRING).description(Document.ID)
        );
    }
}
