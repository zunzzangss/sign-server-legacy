package com.bezzangss.sign.web.internal.documents.document;

import com.bezzangss.sign.web.internal.InternalWebRestDocConstant;
import com.bezzangss.sign.web.internal.InternalWebRestDocTest;
import com.bezzangss.sign.web.internal.InternalWebRestDocTestConfigurer;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.TemplateDocumentInternalWebRestDoc;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.dto.request.TemplateDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.dto.response.TemplateDocumentInternalWebResponse;
import com.bezzangss.sign.web.internal.documents.metadocument._standarddocument.dto.request.StandardDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument._standarddocument.dto.response.StandardDocumentInternalWebResponse;
import com.bezzangss.sign.web.internal.documents.metadocument.standarddocument.StandardDocumentInternalWebRestDoc;
import com.bezzangss.sign.web.internal.resources.resource.ResourceInternalWebRestDoc;
import com.bezzangss.sign.web.internal.resources.resource.dto.response.ResourceInternalWebResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {InternalWebRestDocTestConfigurer.class})
public class DocumentInternalWebRestDocTest extends InternalWebRestDocTest {
    @Test
    public void 문서_조회_ById_성공() throws Exception {
        // given
        MockMultipartFile resourceMultipartFile = ResourceInternalWebRestDoc.getMockMultipartFileSuccess();
        ResultActions resourceCreateByFileResultActions = ResourceInternalWebRestDoc.requestCreateByFile(mockMvc, httpHeaders, resourceMultipartFile);
        ResourceInternalWebResponse resourceInternalWebResponse = super.responseContents(resourceCreateByFileResultActions, new ParameterizedTypeReference<ResourceInternalWebResponse>() {
        });

        TemplateDocumentInternalWebCreateRequest templateDocumentInternalWebCreateRequest = TemplateDocumentInternalWebRestDoc.getCreateRequestSuccess(resourceInternalWebResponse.getId());
        ResultActions templateDocumentCreateResultActions = TemplateDocumentInternalWebRestDoc.create(mockMvc, httpHeaders, objectMapper, templateDocumentInternalWebCreateRequest);
        TemplateDocumentInternalWebResponse templateDocumentInternalWebResponse = super.responseContents(templateDocumentCreateResultActions, new ParameterizedTypeReference<TemplateDocumentInternalWebResponse>() {
        });

        StandardDocumentInternalWebCreateRequest standardDocumentInternalWebCreateRequest = StandardDocumentInternalWebRestDoc.getCreateRequestSuccess(templateDocumentInternalWebResponse.getId());
        ResultActions standardDocumentCreateResultActions = StandardDocumentInternalWebRestDoc.create(mockMvc, httpHeaders, objectMapper, standardDocumentInternalWebCreateRequest);
        StandardDocumentInternalWebResponse standardDocumentInternalWebResponse = super.responseContents(standardDocumentCreateResultActions, new ParameterizedTypeReference<StandardDocumentInternalWebResponse>() {
        });

        String documentId = standardDocumentInternalWebResponse.getDocument().orElseThrow(Exception::new).getId();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("include", Arrays.asList("PUBLISHER", "SIGNER", "CC"));

        // when
        ResultActions resultActions = DocumentInternalWebRestDoc.findById(mockMvc, httpHeaders, documentId, params);

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contents.id").value(documentId))
                .andExpect(jsonPath("$.contents.name").isNotEmpty())
                .andExpect(jsonPath("$.contents.description").isNotEmpty())
                .andExpect(jsonPath("$.contents.status").value("PROCESSING"))
                .andExpect(jsonPath("$.contents.metaDocumentType").value("STANDARD_DOCUMENT"))
                .andExpect(jsonPath("$.contents.metaDocumentId").value(standardDocumentInternalWebResponse.getId()))
                .andExpect(jsonPath("$.contents.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.contents.lastModifiedAt").isNotEmpty())
                .andExpect(jsonPath("$.contents.publisher").isNotEmpty())
                .andExpect(jsonPath("$.contents.publisher.id").isNotEmpty())
                .andExpect(jsonPath("$.contents.signers").isNotEmpty())
                .andExpect(jsonPath("$.contents.signers[*].id").isNotEmpty())
                .andExpect(jsonPath("$.contents.ccs").isNotEmpty())
                .andExpect(jsonPath("$.contents.ccs[*].id").isNotEmpty());

        // restdoc
        resultActions
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
