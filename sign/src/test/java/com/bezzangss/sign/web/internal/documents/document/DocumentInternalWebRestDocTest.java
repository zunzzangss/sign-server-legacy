package com.bezzangss.sign.web.internal.documents.document;

import com.bezzangss.sign.web.internal.InternalWebRestDocConstant;
import com.bezzangss.sign.web.internal.InternalWebRestDocTest;
import com.bezzangss.sign.web.internal.InternalWebRestDocTestConfigurer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {InternalWebRestDocTestConfigurer.class})
public class DocumentInternalWebRestDocTest extends InternalWebRestDocTest {
    @Test
    public void findByIdSuccess() throws Exception {
        DocumentInternalWebRestDoc.findByIdSuccess(mockMvc, httpHeaders, objectMapper)
                .andDo(
                        document("documents/document/find-by-id",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                this.responseFieldsSnippet()
                        )
                );
    }

    private ResponseFieldsSnippet responseFieldsSnippet() {
        return responseFields(
                beneathPath("contents").withSubsectionId("contents"),
                fieldWithPath("id").type(JsonFieldType.STRING).description(InternalWebRestDocConstant.Document.ID),
                fieldWithPath("name").type(JsonFieldType.STRING).description(InternalWebRestDocConstant.Document.NAME),
                fieldWithPath("description").type(JsonFieldType.STRING).description(InternalWebRestDocConstant.Document.DESCRIPTION).optional(),
                fieldWithPath("status").type(JsonFieldType.STRING).description(InternalWebRestDocConstant.Document.STATUS),
                fieldWithPath("metaDocumentType").type(JsonFieldType.STRING).description(InternalWebRestDocConstant.Document.META_DOCUMENT_TYPE),
                fieldWithPath("metaDocumentId").type(JsonFieldType.STRING).description(InternalWebRestDocConstant.Document.META_DOCUMENT_ID),
                fieldWithPath("createdAt").type(JsonFieldType.NUMBER).description(InternalWebRestDocConstant.Document.CREATED_AT),
                fieldWithPath("lastModifiedAt").type(JsonFieldType.NUMBER).description(InternalWebRestDocConstant.Document.LAST_MODIFIED_AT).optional(),
                fieldWithPath("publisher").type(JsonFieldType.OBJECT).description(InternalWebRestDocConstant.Publisher.PUBLISHER).optional(),
                fieldWithPath("publisher.id").type(JsonFieldType.STRING).description(InternalWebRestDocConstant.Publisher.ID),
                fieldWithPath("signers").type(JsonFieldType.ARRAY).description(InternalWebRestDocConstant.Signer.SIGNER).optional(),
                fieldWithPath("signers[].id").type(JsonFieldType.STRING).description(InternalWebRestDocConstant.Signer.ID),
                fieldWithPath("ccs").type(JsonFieldType.ARRAY).description(InternalWebRestDocConstant.Cc.CC).optional(),
                fieldWithPath("ccs[].id").type(JsonFieldType.STRING).description(InternalWebRestDocConstant.Cc.ID)
        );
    }
}
