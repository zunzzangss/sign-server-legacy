package com.bezzangss.sign.web.internal.resources.resource;

import com.bezzangss.sign.web.internal.InternalWebRestDocTest;
import com.bezzangss.sign.web.internal.InternalWebRestDocTestConfigurer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.request.RequestPartsSnippet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.bezzangss.sign.web.internal.InternalWebRestDocConstant.Common;
import static com.bezzangss.sign.web.internal.InternalWebRestDocConstant.Resource;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.partWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParts;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {InternalWebRestDocTestConfigurer.class})
public class ResourceInternalWebRestDocTest extends InternalWebRestDocTest {
    @Test
    public void create() throws Exception {
        ResourceInternalWebRestDoc.create(mockMvc, httpHeaders)
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
                partWithName("file").description(Common.REQUIRED + "멀티파트 파일")
        );
    }

    private ResponseFieldsSnippet responseFieldsSnippet() {
        return responseFields(
                beneathPath("contents").withSubsectionId("contents"),
                fieldWithPath("id").type(JsonFieldType.STRING).description(Resource.ID),
                fieldWithPath("type").type(JsonFieldType.STRING).description(Resource.TYPE),
                fieldWithPath("source").type(JsonFieldType.STRING).description(Resource.SOURCE),
                fieldWithPath("size").type(JsonFieldType.NUMBER).description(Resource.SIZE),
                fieldWithPath("createdAt").type(JsonFieldType.NUMBER).description(Resource.CREATED_AT),
                fieldWithPath("lastModifiedAt").type(JsonFieldType.NUMBER).description(Resource.LAST_MODIFIED_AT).optional()
        );
    }
}
