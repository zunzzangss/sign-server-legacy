package com.bezzangss.sign.web.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

@Transactional
@WebAppConfiguration
@ContextConfiguration(name = "com.bezzangss.sign")
public class InternalWebRestDocTest {
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("build/generated-snippets-web-internal");

    @Autowired
    protected ObjectMapper objectMapper;

    protected HttpHeaders httpHeaders = new HttpHeaders();

    @Before
    public void before() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(restDocumentation))
                .build();

        httpHeaders = this.getHttpHeader();
    }

    private HttpHeaders getHttpHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();

        return httpHeaders;
    }

    protected ResponseFieldsSnippet responseFieldsSnippet(JsonFieldType jsonFieldType) {
        return responseFields(
                fieldWithPath("success").type(JsonFieldType.BOOLEAN).description(InternalWebRestDocConstant.SUCCESS),
                fieldWithPath("error").type(JsonFieldType.OBJECT).description(InternalWebRestDocConstant.ERROR).optional(),
                subsectionWithPath("contents").type(jsonFieldType).description(InternalWebRestDocConstant.CONTENTS)
        );
    }
}
