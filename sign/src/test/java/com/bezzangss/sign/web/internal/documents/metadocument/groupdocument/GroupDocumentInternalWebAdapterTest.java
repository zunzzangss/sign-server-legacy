package com.bezzangss.sign.web.internal.documents.metadocument.groupdocument;

import com.bezzangss.sign.web.internal.InternalWebAdapterTestConstant.*;
import com.bezzangss.sign.web.internal.InternalWebAdapterTest;
import com.bezzangss.sign.web.internal.InternalWebAdapterTestConfigurer;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.TemplateDocumentInternalWebAdapterTestSupport;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.dto.request.TemplateDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.dto.response.TemplateDocumentInternalWebResponse;
import com.bezzangss.sign.web.internal.documents.metadocument._groupdocument.dto.request.GroupDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument._groupdocument.dto.response.GroupDocumentInternalWebResponse;
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
public class GroupDocumentInternalWebAdapterTest extends InternalWebAdapterTest {
    @Test
    public void 그룹문서_생성_성공() throws Exception {
        // given
        MockMultipartFile resourceMultipartFile = ResourceInternalWebAdapterTestSupport.getMockMultipartFileSuccess();
        ResultActions resourceCreateByFileResultActions = ResourceInternalWebAdapterTestSupport.requestCreateByFile(mockMvc, httpHeaders, resourceMultipartFile);
        ResourceInternalWebResponse resourceInternalWebResponse = super.responseContents(resourceCreateByFileResultActions, new ParameterizedTypeReference<ResourceInternalWebResponse>() {
        });

        TemplateDocumentInternalWebCreateRequest templateDocumentInternalWebCreateRequest = TemplateDocumentInternalWebAdapterTestSupport.getCreateRequestSuccess(resourceInternalWebResponse.getId());
        ResultActions templateDocumentCreateResultActions = TemplateDocumentInternalWebAdapterTestSupport.create(mockMvc, httpHeaders, objectMapper, templateDocumentInternalWebCreateRequest);
        TemplateDocumentInternalWebResponse templateDocumentInternalWebResponse = super.responseContents(templateDocumentCreateResultActions, new ParameterizedTypeReference<TemplateDocumentInternalWebResponse>() {
        });

        GroupDocumentInternalWebCreateRequest groupDocumentInternalWebCreateRequest = GroupDocumentInternalWebAdapterTestSupport.getCreateRequestSuccess(templateDocumentInternalWebResponse.getId());

        // when
        ResultActions resultActions = GroupDocumentInternalWebAdapterTestSupport.create(mockMvc, httpHeaders, objectMapper, groupDocumentInternalWebCreateRequest);

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contents.id").isNotEmpty())
                .andExpect(jsonPath("$.contents.name").value(groupDocumentInternalWebCreateRequest.getName()))
                .andExpect(jsonPath("$.contents.description").value(groupDocumentInternalWebCreateRequest.getDescription()))
                .andExpect(jsonPath("$.contents.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.contents.lastModifiedAt").isEmpty())
                .andExpect(jsonPath("$.contents.documents").isNotEmpty())
                .andExpect(jsonPath("$.contents.documents[*].id").isNotEmpty());

        // restdoc
        resultActions
                .andDo(
                        document("documents/metadocument/groupdocument/create",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                this.requestFieldsSnippet(),
                                super.responseFieldsSnippet(JsonFieldType.OBJECT),
                                this.responseFieldsSnippet()
                        )
                );
    }

    @Test
    public void 그룹문서_조회_ById_성공() throws Exception {
        // given
        MockMultipartFile resourceMultipartFile = ResourceInternalWebAdapterTestSupport.getMockMultipartFileSuccess();
        ResultActions resourceCreateByFileResultActions = ResourceInternalWebAdapterTestSupport.requestCreateByFile(mockMvc, httpHeaders, resourceMultipartFile);
        ResourceInternalWebResponse resourceInternalWebResponse = super.responseContents(resourceCreateByFileResultActions, new ParameterizedTypeReference<ResourceInternalWebResponse>() {
        });

        TemplateDocumentInternalWebCreateRequest templateDocumentInternalWebCreateRequest = TemplateDocumentInternalWebAdapterTestSupport.getCreateRequestSuccess(resourceInternalWebResponse.getId());
        ResultActions templateDocumentCreateResultActions = TemplateDocumentInternalWebAdapterTestSupport.create(mockMvc, httpHeaders, objectMapper, templateDocumentInternalWebCreateRequest);
        TemplateDocumentInternalWebResponse templateDocumentInternalWebResponse = super.responseContents(templateDocumentCreateResultActions, new ParameterizedTypeReference<TemplateDocumentInternalWebResponse>() {
        });

        GroupDocumentInternalWebCreateRequest groupDocumentInternalWebCreateRequest = GroupDocumentInternalWebAdapterTestSupport.getCreateRequestSuccess(templateDocumentInternalWebResponse.getId());
        ResultActions groupDocumentCreateResultActions = GroupDocumentInternalWebAdapterTestSupport.create(mockMvc, httpHeaders, objectMapper, groupDocumentInternalWebCreateRequest);
        GroupDocumentInternalWebResponse groupDocumentInternalWebResponse = super.responseContents(groupDocumentCreateResultActions, new ParameterizedTypeReference<GroupDocumentInternalWebResponse>() {
        });

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("include", "DOCUMENT");

        // when
        ResultActions resultActions = GroupDocumentInternalWebAdapterTestSupport.findById(mockMvc, httpHeaders, groupDocumentInternalWebResponse.getId(), params);

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contents.id").isNotEmpty())
                .andExpect(jsonPath("$.contents.name").value(groupDocumentInternalWebCreateRequest.getName()))
                .andExpect(jsonPath("$.contents.description").value(groupDocumentInternalWebCreateRequest.getDescription()))
                .andExpect(jsonPath("$.contents.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.contents.lastModifiedAt").isEmpty())
                .andExpect(jsonPath("$.contents.documents").isNotEmpty())
                .andExpect(jsonPath("$.contents.documents[*].id").isNotEmpty());

        // restdoc
        resultActions
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
