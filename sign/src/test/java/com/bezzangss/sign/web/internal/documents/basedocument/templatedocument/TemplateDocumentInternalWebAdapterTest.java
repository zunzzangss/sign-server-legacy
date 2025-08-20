package com.bezzangss.sign.web.internal.documents.basedocument.templatedocument;

import com.bezzangss.sign.web.internal.InternalWebAdapterTest;
import com.bezzangss.sign.web.internal.InternalWebAdapterTestConfigurer;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.dto.request.TemplateDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.resources.resource.ResourceInternalWebAdapterTestSupport;
import com.bezzangss.sign.web.internal.resources.resource.dto.response.ResourceInternalWebResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;

import static com.bezzangss.sign.web.internal.InternalWebAdapterTestConstant.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {InternalWebAdapterTestConfigurer.class})
public class TemplateDocumentInternalWebAdapterTest extends InternalWebAdapterTest {
    @Test
    public void 서식문서_생성_성공() throws Exception {
        // given
        MockMultipartFile resourceMultipartFile = ResourceInternalWebAdapterTestSupport.getMockMultipartFileSuccess();
        ResultActions ResourceCreateByFileResultActions = ResourceInternalWebAdapterTestSupport.requestCreateByFile(mockMvc, httpHeaders, resourceMultipartFile);
        ResourceInternalWebResponse resourceInternalWebResponse = super.responseContents(ResourceCreateByFileResultActions, new ParameterizedTypeReference<ResourceInternalWebResponse>() {
        });

        TemplateDocumentInternalWebCreateRequest templateDocumentInternalWebCreateRequest = TemplateDocumentInternalWebAdapterTestSupport.getCreateRequestSuccess(resourceInternalWebResponse.getId());

        // when
        ResultActions resultActions = TemplateDocumentInternalWebAdapterTestSupport.create(mockMvc, httpHeaders, objectMapper, templateDocumentInternalWebCreateRequest);

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contents.id").isNotEmpty())
                .andExpect(jsonPath("$.contents.name").value(templateDocumentInternalWebCreateRequest.getName()))
                .andExpect(jsonPath("$.contents.description").value(templateDocumentInternalWebCreateRequest.getDescription()))
                .andExpect(jsonPath("$.contents.status").value("NONE"))
                .andExpect(jsonPath("$.contents.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.contents.lastModifiedAt").isEmpty())
                .andExpect(jsonPath("$.contents.id").isNotEmpty());

        // restdoc
        resultActions
                .andDo(
                        document("documents/basedocument/templatedocument/create",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                this.requestFieldsSnippet(),
                                super.responseFieldsSnippet(JsonFieldType.OBJECT),
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
