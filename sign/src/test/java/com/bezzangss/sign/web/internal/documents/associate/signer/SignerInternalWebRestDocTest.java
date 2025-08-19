package com.bezzangss.sign.web.internal.documents.associate.signer;

import com.bezzangss.sign.web.internal.InternalWebRestDocTest;
import com.bezzangss.sign.web.internal.InternalWebRestDocTestConfigurer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.request.PathParametersSnippet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.bezzangss.sign.web.internal.InternalWebRestDocConstant.Common;
import static com.bezzangss.sign.web.internal.InternalWebRestDocConstant.Signer;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {InternalWebRestDocTestConfigurer.class})
public class SignerInternalWebRestDocTest extends InternalWebRestDocTest {
    @Test
    public void signSuccess() throws Exception {
        SignerInternalWebRestDoc.signSuccess(mockMvc, httpHeaders, objectMapper)
                .andDo(
                        document("documents/associate/signer/sign",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                this.pathParametersSnippet(),
                                super.responseFieldsSnippet(JsonFieldType.OBJECT)
                        )
                )
                .andDo(
                        document("documents/associate/signer/sign",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                this.responseFieldsSnippet()
                        )
                );
    }

    @Test
    public void signAllSuccess() throws Exception {
        SignerInternalWebRestDoc.signAllSuccess(mockMvc, httpHeaders, objectMapper);
    }

    @Test
    public void findAllByDocumentIdSuccess() throws Exception {
        SignerInternalWebRestDoc.findAllByDocumentIdSuccess(mockMvc, httpHeaders, objectMapper)
                .andDo(
                        document("documents/associate/signer/find-all-by-document-id",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                this.responseFieldsSnippet()
                        )
                );
    }

    private PathParametersSnippet pathParametersSnippet() {
        return pathParameters(
                parameterWithName("id").description(Common.REQUIRED + Signer.ID)
        );
    }

    private ResponseFieldsSnippet responseFieldsSnippet() {
        return responseFields(
                beneathPath("contents").withSubsectionId("contents"),
                fieldWithPath("id").type(JsonFieldType.STRING).description(Signer.ID),
                fieldWithPath("name").type(JsonFieldType.STRING).description(Signer.NAME),
                fieldWithPath("email").type(JsonFieldType.STRING).description(Signer.EMAIL).optional(),
                fieldWithPath("cellPhone").type(JsonFieldType.STRING).description(Signer.CELL_PHONE).optional(),
                fieldWithPath("order").type(JsonFieldType.NUMBER).description(Signer.ORDER),
                fieldWithPath("status").type(JsonFieldType.STRING).description(Signer.STATUS),
                fieldWithPath("documentId").type(JsonFieldType.STRING).description(Signer.DOCUMENT_ID),
                fieldWithPath("createdAt").type(JsonFieldType.NUMBER).description(Signer.CREATED_AT),
                fieldWithPath("lastModifiedAt").type(JsonFieldType.NUMBER).description(Signer.LAST_MODIFIED_AT).optional()
        );
    }
}
