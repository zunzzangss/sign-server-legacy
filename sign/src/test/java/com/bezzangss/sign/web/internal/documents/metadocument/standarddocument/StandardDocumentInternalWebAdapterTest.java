package com.bezzangss.sign.web.internal.documents.metadocument.standarddocument;

import com.bezzangss.sign.web.internal.InternalWebAdapterTestConstant.*;
import com.bezzangss.sign.web.internal.InternalWebAdapterTest;
import com.bezzangss.sign.web.internal.InternalWebAdapterTestConfigurer;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.TemplateDocumentInternalWebAdapterTestSupport;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.dto.request.TemplateDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.dto.response.TemplateDocumentInternalWebResponse;
import com.bezzangss.sign.web.internal.documents.metadocument._standarddocument.dto.request.StandardDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument._standarddocument.dto.response.StandardDocumentInternalWebResponse;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {InternalWebAdapterTestConfigurer.class})
public class StandardDocumentInternalWebAdapterTest extends InternalWebAdapterTest {
    @Test
    public void 기본문서_생성_성공() throws Exception {
        // given
        MockMultipartFile resourceMultipartFile = ResourceInternalWebAdapterTestSupport.getMockMultipartFileSuccess();
        ResultActions resourceCreateByFileResultActions = ResourceInternalWebAdapterTestSupport.requestCreateByFile(mockMvc, httpHeaders, resourceMultipartFile);
        ResourceInternalWebResponse resourceInternalWebResponse = super.responseContents(resourceCreateByFileResultActions, new ParameterizedTypeReference<ResourceInternalWebResponse>() {
        });

        TemplateDocumentInternalWebCreateRequest templateDocumentInternalWebCreateRequest = TemplateDocumentInternalWebAdapterTestSupport.getCreateRequestSuccess(resourceInternalWebResponse.getId());
        ResultActions templateDocumentCreateResultActions = TemplateDocumentInternalWebAdapterTestSupport.create(mockMvc, httpHeaders, objectMapper, templateDocumentInternalWebCreateRequest);
        TemplateDocumentInternalWebResponse templateDocumentInternalWebResponse = super.responseContents(templateDocumentCreateResultActions, new ParameterizedTypeReference<TemplateDocumentInternalWebResponse>() {
        });

        StandardDocumentInternalWebCreateRequest standardDocumentInternalWebCreateRequest = StandardDocumentInternalWebAdapterTestSupport.getCreateRequestSuccess(templateDocumentInternalWebResponse.getId());

        // when
        ResultActions resultActions = StandardDocumentInternalWebAdapterTestSupport.create(mockMvc, httpHeaders, objectMapper, standardDocumentInternalWebCreateRequest);

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contents.id").isNotEmpty())
                .andExpect(jsonPath("$.contents.name").value(standardDocumentInternalWebCreateRequest.getName()))
                .andExpect(jsonPath("$.contents.description").value(standardDocumentInternalWebCreateRequest.getDescription()))
                .andExpect(jsonPath("$.contents.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.contents.lastModifiedAt").isEmpty())
                .andExpect(jsonPath("$.contents.document").isNotEmpty())
                .andExpect(jsonPath("$.contents.document.id").isNotEmpty());

        // restdoc
        resultActions
                .andDo(
                        document("documents/metadocument/standarddocument/create",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                this.requestFieldsSnippet(),
                                super.responseFieldsSnippet(JsonFieldType.OBJECT),
                                this.responseFieldsSnippet()
                        )
                );
    }

    @Test
    public void 기본문서_조회_ById_성공() throws Exception {
        // given
        MockMultipartFile resourceMultipartFile = ResourceInternalWebAdapterTestSupport.getMockMultipartFileSuccess();
        ResultActions resourceCreateByFileResultActions = ResourceInternalWebAdapterTestSupport.requestCreateByFile(mockMvc, httpHeaders, resourceMultipartFile);
        ResourceInternalWebResponse resourceInternalWebResponse = super.responseContents(resourceCreateByFileResultActions, new ParameterizedTypeReference<ResourceInternalWebResponse>() {
        });

        TemplateDocumentInternalWebCreateRequest templateDocumentInternalWebCreateRequest = TemplateDocumentInternalWebAdapterTestSupport.getCreateRequestSuccess(resourceInternalWebResponse.getId());
        ResultActions templateDocumentCreateResultActions = TemplateDocumentInternalWebAdapterTestSupport.create(mockMvc, httpHeaders, objectMapper, templateDocumentInternalWebCreateRequest);
        TemplateDocumentInternalWebResponse templateDocumentInternalWebResponse = super.responseContents(templateDocumentCreateResultActions, new ParameterizedTypeReference<TemplateDocumentInternalWebResponse>() {
        });

        StandardDocumentInternalWebCreateRequest standardDocumentInternalWebCreateRequest = StandardDocumentInternalWebAdapterTestSupport.getCreateRequestSuccess(templateDocumentInternalWebResponse.getId());
        ResultActions standardDocumentCreateResultActions = StandardDocumentInternalWebAdapterTestSupport.create(mockMvc, httpHeaders, objectMapper, standardDocumentInternalWebCreateRequest);
        StandardDocumentInternalWebResponse standardDocumentInternalWebResponse = super.responseContents(standardDocumentCreateResultActions, new ParameterizedTypeReference<StandardDocumentInternalWebResponse>() {
        });

        MultiValueMap<String, String> params =  new LinkedMultiValueMap<>();
        params.add("include", "DOCUMENT");

        // when
        ResultActions resultActions = StandardDocumentInternalWebAdapterTestSupport.findById(mockMvc, httpHeaders, standardDocumentInternalWebResponse.getId(), params);

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contents.id").isNotEmpty())
                .andExpect(jsonPath("$.contents.name").value(standardDocumentInternalWebCreateRequest.getName()))
                .andExpect(jsonPath("$.contents.description").value(standardDocumentInternalWebCreateRequest.getDescription()))
                .andExpect(jsonPath("$.contents.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.contents.lastModifiedAt").isEmpty())
                .andExpect(jsonPath("$.contents.document").isNotEmpty())
                .andExpect(jsonPath("$.contents.document.id").isNotEmpty());

        // restdoc
        resultActions
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
