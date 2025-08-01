package com.bezzangss.sign.web.internal.resources.resource;

import com.bezzangss.sign.web.internal.InternalWebRestDocConstant;
import com.bezzangss.sign.web.internal.InternalWebRestDocTest;
import com.bezzangss.sign.web.internal.InternalWebRestDocTestConfigurer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.request.RequestPartsSnippet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.fileUpload;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.partWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParts;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {InternalWebRestDocTestConfigurer.class})
public class ResourceInternalWebRestDocTest extends InternalWebRestDocTest {
    @Test
    public void create() throws Exception {
        mockMvc.perform(
                        fileUpload("/internal/v1/resource/create-by-file")
                                .file(
                                        new MockMultipartFile(
                                                "file",
                                                UUID.randomUUID().toString(),
                                                MediaType.TEXT_PLAIN_VALUE,
                                                UUID.randomUUID().toString().getBytes()
                                        )
                                )
                                .headers(httpHeaders)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(
                        document("resources/resource/create-by-file",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                this.requestPartsSnippet(),
                                super.responseFieldsSnippet(JsonFieldType.OBJECT)
                        )
                )
                .andDo(
                        document("resources/resource/create-by-file",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                this.responseFieldsSnippet()
                        )
                )
        ;
    }

    private RequestPartsSnippet requestPartsSnippet() {
        return requestParts(
                partWithName("file").description("멀티파트 파일")
        );
    }

    private ResponseFieldsSnippet responseFieldsSnippet() {
        return responseFields(
                beneathPath("contents").withSubsectionId("contents"),
                fieldWithPath("id").type(JsonFieldType.STRING).description(InternalWebRestDocConstant.Resource.ID),
                fieldWithPath("type").type(JsonFieldType.STRING).description(InternalWebRestDocConstant.Resource.TYPE),
                fieldWithPath("source").type(JsonFieldType.STRING).description(InternalWebRestDocConstant.Resource.SOURCE),
                fieldWithPath("size").type(JsonFieldType.NUMBER).description(InternalWebRestDocConstant.Resource.SIZE),
                fieldWithPath("createdAt").type(JsonFieldType.NUMBER).description(InternalWebRestDocConstant.Resource.CREATED_AT),
                fieldWithPath("lastModifiedAt").type(JsonFieldType.NUMBER).description(InternalWebRestDocConstant.Resource.LAST_MODIFIED_AT).optional()
        );
    }
}
