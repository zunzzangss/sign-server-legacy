package com.bezzangss.sign.web.internal.documents.basedocument.templatedocument;

import com.bezzangss.sign.web.internal.InternalWebRestDocTest;
import com.bezzangss.sign.web.internal.InternalWebRestDocTestConfigurer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.bezzangss.sign.web.internal.InternalWebRestDocConstant.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {InternalWebRestDocTestConfigurer.class})
public class TemplateDocumentInternalWebRestDocTest extends InternalWebRestDocTest {
    @Test
    public void createSuccess() throws Exception {
        TemplateDocumentInternalWebRestDoc.createSuccess(mockMvc, httpHeaders, objectMapper)
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
                );
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
                fieldWithPath("description").type(JsonFieldType.STRING).description(TemplateDocument.DESCRIPTION).optional(),
                fieldWithPath("status").type(JsonFieldType.STRING).description(TemplateDocument.STATUS),
                fieldWithPath("createdAt").type(JsonFieldType.NUMBER).description(TemplateDocument.CREATED_AT),
                fieldWithPath("lastModifiedAt").type(JsonFieldType.NUMBER).description(TemplateDocument.LAST_MODIFIED_AT).optional()
        );
    }
}
