package com.bezzangss.sign.web.internal.documents.metadocument.groupdocument;

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
public class GroupDocumentInternalWebRestDocTest extends InternalWebRestDocTest {
    @Test
    public void create() throws Exception {
        GroupDocumentInternalWebRestDoc.createSuccess(mockMvc, httpHeaders, objectMapper)
                .andDo(
                        document("documents/metadocument/groupdocument/create",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                this.requestFieldsSnippet(),
                                super.responseFieldsSnippet(JsonFieldType.OBJECT)
                        )
                )
                .andDo(
                        document("documents/metadocument/groupdocument/create",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                this.responseFieldsSnippet()
                        )
                );
    }

    @Test
    public void findByIdSuccess() throws Exception {
        GroupDocumentInternalWebRestDoc.findByIdSuccess(mockMvc, httpHeaders, objectMapper)
                .andDo(
                        document("documents/metadocument/groupdocument/find-by-id",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                this.responseFieldsSnippet()
                        )
                );
    }

    private RequestFieldsSnippet requestFieldsSnippet() {
        return requestFields(
                fieldWithPath("name").type(JsonFieldType.STRING).description(Common.REQUIRED + GroupDocument.NAME),
                fieldWithPath("description").type(JsonFieldType.STRING).description(Common.OPTIONAL + GroupDocument.DESCRIPTION).optional(),
                fieldWithPath("baseDocument").type(JsonFieldType.OBJECT).description(Common.REQUIRED + BaseDocument.BASE_DOCUMENT),
                fieldWithPath("baseDocument.id").type(JsonFieldType.STRING).description(Common.REQUIRED + BaseDocument.ID),
                fieldWithPath("baseDocument.type").type(JsonFieldType.STRING).description(Common.REQUIRED + BaseDocument.TYPE),
                fieldWithPath("documents").type(JsonFieldType.ARRAY).description(Common.REQUIRED + Document.DOCUMENT),
                fieldWithPath("documents[].name").type(JsonFieldType.STRING).description(Common.REQUIRED + Document.NAME),
                fieldWithPath("documents[].description").type(JsonFieldType.STRING).description(Common.OPTIONAL + Document.DESCRIPTION).optional(),

                fieldWithPath("documents[].publisher").type(JsonFieldType.OBJECT).description(Common.REQUIRED + Publisher.PUBLISHER),
                fieldWithPath("documents[].publisher.name").type(JsonFieldType.STRING).description(Common.REQUIRED + Publisher.NAME),
                fieldWithPath("documents[].publisher.email").type(JsonFieldType.STRING).description(Common.OPTIONAL + Publisher.EMAIL),
                fieldWithPath("documents[].publisher.cellPhone").type(JsonFieldType.STRING).description(Common.OPTIONAL + Publisher.CELL_PHONE),

                fieldWithPath("documents[].signers").type(JsonFieldType.ARRAY).description(Common.REQUIRED + Signer.SIGNER),
                fieldWithPath("documents[].signers[].name").type(JsonFieldType.STRING).description(Common.REQUIRED + Signer.NAME),
                fieldWithPath("documents[].signers[].email").type(JsonFieldType.STRING).description(Common.OPTIONAL + Signer.EMAIL),
                fieldWithPath("documents[].signers[].cellPhone").type(JsonFieldType.STRING).description(Common.OPTIONAL + Signer.CELL_PHONE),
                fieldWithPath("documents[].signers[].order").type(JsonFieldType.NUMBER).description(Common.OPTIONAL + Signer.ORDER),

                fieldWithPath("documents[].ccs").type(JsonFieldType.ARRAY).description(Common.OPTIONAL + Cc.CC),
                fieldWithPath("documents[].ccs[].name").type(JsonFieldType.STRING).description(Common.REQUIRED + Cc.NAME),
                fieldWithPath("documents[].ccs[].email").type(JsonFieldType.STRING).description(Common.OPTIONAL + Cc.EMAIL),
                fieldWithPath("documents[].ccs[].cellPhone").type(JsonFieldType.STRING).description(Common.OPTIONAL + Cc.CELL_PHONE)
        );
    }

    private ResponseFieldsSnippet responseFieldsSnippet() {
        return responseFields(
                beneathPath("contents").withSubsectionId("contents"),
                fieldWithPath("id").type(JsonFieldType.STRING).description(GroupDocument.ID),
                fieldWithPath("name").type(JsonFieldType.STRING).description(GroupDocument.NAME),
                fieldWithPath("description").type(JsonFieldType.STRING).description(GroupDocument.DESCRIPTION).optional(),
                fieldWithPath("createdAt").type(JsonFieldType.NUMBER).description(GroupDocument.CREATED_AT),
                fieldWithPath("lastModifiedAt").type(JsonFieldType.NUMBER).description(GroupDocument.LAST_MODIFIED_AT).optional(),
                fieldWithPath("documents").type(JsonFieldType.ARRAY).description(Document.DOCUMENT).optional(),
                fieldWithPath("documents[].id").type(JsonFieldType.STRING).description(Document.ID)
        );
    }
}
