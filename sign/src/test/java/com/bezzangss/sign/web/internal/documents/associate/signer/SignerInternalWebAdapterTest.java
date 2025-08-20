package com.bezzangss.sign.web.internal.documents.associate.signer;

import com.bezzangss.sign.web.internal.InternalWebAdapterTest;
import com.bezzangss.sign.web.internal.InternalWebAdapterTestConfigurer;
import com.bezzangss.sign.web.internal.documents.associate.signer.dto.response.SignerInternalWebResponse;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.TemplateDocumentInternalWebAdapterTestSupport;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.dto.request.TemplateDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.dto.response.TemplateDocumentInternalWebResponse;
import com.bezzangss.sign.web.internal.documents.document.DocumentInternalWebAdapterTestSupport;
import com.bezzangss.sign.web.internal.documents.metadocument._standarddocument.dto.request.StandardDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument._standarddocument.dto.response.StandardDocumentInternalWebResponse;
import com.bezzangss.sign.web.internal.documents.metadocument.standarddocument.StandardDocumentInternalWebAdapterTestSupport;
import com.bezzangss.sign.web.internal.resources.resource.ResourceInternalWebAdapterTestSupport;
import com.bezzangss.sign.web.internal.resources.resource.dto.response.ResourceInternalWebResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.request.PathParametersSnippet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.bezzangss.sign.web.internal.InternalWebAdapterTestConstant.Common;
import static com.bezzangss.sign.web.internal.InternalWebAdapterTestConstant.Signer;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {InternalWebAdapterTestConfigurer.class})
public class SignerInternalWebAdapterTest extends InternalWebAdapterTest {
    @Test
    public void 서명자_서명_성공() throws Exception {
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

        String documentId = standardDocumentInternalWebResponse.getDocument().orElseThrow(Exception::new).getId();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("documentId", documentId);
        ResultActions signerFindAllByIdResultActions = SignerInternalWebAdapterTestSupport.findAllByDocumentId(mockMvc, httpHeaders, params);
        List<SignerInternalWebResponse> signerInternalWebResponses = super.responseContents(signerFindAllByIdResultActions, new ParameterizedTypeReference<List<SignerInternalWebResponse>>() {
                })
                .stream()
                .sorted(Comparator.comparing(SignerInternalWebResponse::getOrder))
                .collect(Collectors.toList());

        Assert.assertTrue(!signerInternalWebResponses.isEmpty());
        String signerId = signerInternalWebResponses.stream()
                .sorted(Comparator.comparing(SignerInternalWebResponse::getOrder))
                .map(SignerInternalWebResponse::getId)
                .findAny()
                .orElseThrow(() -> new Exception("Signer not found"));

        // when
        ResultActions signerSignActions = SignerInternalWebAdapterTestSupport.sign(mockMvc, httpHeaders, signerId);

        // then
        signerSignActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contents.status").value("SIGNED"));

        DocumentInternalWebAdapterTestSupport.findById(mockMvc, httpHeaders, documentId, new LinkedMultiValueMap<>())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contents.status").value("PROCESSING"));

        // restdoc
        signerSignActions
                .andDo(
                        document("documents/associate/signer/sign",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                this.pathParametersSnippet(),
                                super.responseFieldsSnippet(JsonFieldType.OBJECT),
                                this.responseFieldsSnippet()
                        )
                );
    }

    @Test
    public void 서명자_전체_서명_성공() throws Exception {
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

        String documentId = standardDocumentInternalWebResponse.getDocument().orElseThrow(Exception::new).getId();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("documentId", documentId);
        ResultActions signerFindAllByIdResultActions = SignerInternalWebAdapterTestSupport.findAllByDocumentId(mockMvc, httpHeaders, params);
        List<SignerInternalWebResponse> signerInternalWebResponses = super.responseContents(signerFindAllByIdResultActions, new ParameterizedTypeReference<List<SignerInternalWebResponse>>() {
                })
                .stream()
                .sorted(Comparator.comparing(SignerInternalWebResponse::getOrder))
                .collect(Collectors.toList());

        Assert.assertTrue(!signerInternalWebResponses.isEmpty());

        // when
        List<ResultActions> signerSignResultActions = new ArrayList<>();

        for (SignerInternalWebResponse signerInternalWebResponse : signerInternalWebResponses) {
            signerSignResultActions.add(SignerInternalWebAdapterTestSupport.sign(mockMvc, httpHeaders, signerInternalWebResponse.getId()));
        }

        ResultActions documentFindByIdResultActions = DocumentInternalWebAdapterTestSupport.findById(mockMvc, httpHeaders, documentId, new LinkedMultiValueMap<>());

        // then
        for (ResultActions resultAction : signerSignResultActions) {
            resultAction
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.contents.status").value("SIGNED"));
        }

        documentFindByIdResultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contents.status").value("COMPLETED"));
    }

    @Test
    public void 서명자_전체_조회_ByDocumentId_성공() throws Exception {
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

        String documentId = standardDocumentInternalWebResponse.getDocument().orElseThrow(Exception::new).getId();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("documentId", documentId);

        // when
        ResultActions resultActions = SignerInternalWebAdapterTestSupport.findAllByDocumentId(mockMvc, httpHeaders, params);

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contents[*].id").isNotEmpty())
                .andExpect(jsonPath("$.contents[*].name").isNotEmpty())
                .andExpect(jsonPath("$.contents[*].email").isNotEmpty())
                .andExpect(jsonPath("$.contents[*].cellPhone").isNotEmpty())
                .andExpect(jsonPath("$.contents[*].order").isNotEmpty())
                .andExpect(jsonPath("$.contents[*].status").isNotEmpty())
                .andExpect(jsonPath("$.contents[*].documentId").isNotEmpty())
                .andExpect(jsonPath("$.contents[*].createdAt").isNotEmpty())
                .andExpect(jsonPath("$.contents[*].lastModifiedAt").isNotEmpty());

        // restdoc
        resultActions
                .andDo(
                        document("documents/associate/signer/find-all-by-document-id",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                super.responseFieldsSnippet(JsonFieldType.ARRAY),
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
