package com.bezzangss.sign.web.internal.documents.associate.signer;

import com.bezzangss.sign.web.internal.InternalWebRestDocTest;
import com.bezzangss.sign.web.internal.InternalWebRestDocTestConfigurer;
import com.bezzangss.sign.web.internal.documents.associate.signer.dto.response.SignerInternalWebResponse;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.TemplateDocumentInternalWebRestDoc;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.dto.request.TemplateDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.dto.response.TemplateDocumentInternalWebResponse;
import com.bezzangss.sign.web.internal.documents.document.DocumentInternalWebRestDoc;
import com.bezzangss.sign.web.internal.documents.metadocument._standarddocument.dto.request.StandardDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument._standarddocument.dto.response.StandardDocumentInternalWebResponse;
import com.bezzangss.sign.web.internal.documents.metadocument.standarddocument.StandardDocumentInternalWebRestDoc;
import com.bezzangss.sign.web.internal.resources.resource.ResourceInternalWebRestDoc;
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

import static com.bezzangss.sign.web.internal.InternalWebRestDocConstant.Common;
import static com.bezzangss.sign.web.internal.InternalWebRestDocConstant.Signer;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {InternalWebRestDocTestConfigurer.class})
public class SignerInternalWebRestDocTest extends InternalWebRestDocTest {
    @Test
    public void 서명자_서명_성공() throws Exception {
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
        params.add("documentId", documentId);
        ResultActions signerFindAllByIdResultActions = SignerInternalWebRestDoc.findAllByDocumentId(mockMvc, httpHeaders, params);
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
        ResultActions signerSignActions = SignerInternalWebRestDoc.sign(mockMvc, httpHeaders, signerId);

        // then
        signerSignActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contents.status").value("SIGNED"));

        DocumentInternalWebRestDoc.findById(mockMvc, httpHeaders, documentId, new LinkedMultiValueMap<>())
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
        params.add("documentId", documentId);
        ResultActions signerFindAllByIdResultActions = SignerInternalWebRestDoc.findAllByDocumentId(mockMvc, httpHeaders, params);
        List<SignerInternalWebResponse> signerInternalWebResponses = super.responseContents(signerFindAllByIdResultActions, new ParameterizedTypeReference<List<SignerInternalWebResponse>>() {
                })
                .stream()
                .sorted(Comparator.comparing(SignerInternalWebResponse::getOrder))
                .collect(Collectors.toList());

        Assert.assertTrue(!signerInternalWebResponses.isEmpty());

        // when
        List<ResultActions> signerSignResultActions = new ArrayList<>();

        for (SignerInternalWebResponse signerInternalWebResponse : signerInternalWebResponses) {
            signerSignResultActions.add(SignerInternalWebRestDoc.sign(mockMvc, httpHeaders, signerInternalWebResponse.getId()));
        }

        ResultActions documentFindByIdResultActions = DocumentInternalWebRestDoc.findById(mockMvc, httpHeaders, documentId, new LinkedMultiValueMap<>());

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
        params.add("documentId", documentId);

        // when
        ResultActions resultActions = SignerInternalWebRestDoc.findAllByDocumentId(mockMvc, httpHeaders, params);

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
