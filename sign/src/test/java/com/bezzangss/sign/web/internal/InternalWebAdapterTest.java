package com.bezzangss.sign.web.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.rules.TemporaryFolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.UnsupportedEncodingException;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

@Transactional
@WebAppConfiguration
@ContextConfiguration(name = "com.bezzangss.sign")
public class InternalWebAdapterTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Environment environment;

    @Autowired
    protected ObjectMapper objectMapper;

    protected MockMvc mockMvc;

    protected HttpHeaders httpHeaders;

    @ClassRule
    public static final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("build/generated-snippets-web-internal");

    @ClassRule
    public static TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void before() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(restDocumentation))
                .build();
        this.httpHeaders = this.getHttpHeader();

        System.setProperty("com.bezzangss.sign.storage.file-system.prefix-path", new File(temporaryFolder.getRoot(), "sign/resources").getAbsolutePath());
    }

    private HttpHeaders getHttpHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();

        return httpHeaders;
    }

    protected ResponseFieldsSnippet responseFieldsSnippet(JsonFieldType jsonFieldType) {
        return responseFields(
                fieldWithPath("success").type(JsonFieldType.BOOLEAN).description(InternalWebAdapterTestConstant.SUCCESS),
                fieldWithPath("error").type(JsonFieldType.OBJECT).description(InternalWebAdapterTestConstant.ERROR).optional(),
                subsectionWithPath("contents").type(jsonFieldType).description(InternalWebAdapterTestConstant.CONTENTS)
        );
    }

    protected <T> T responseContents(ResultActions resultActions, ParameterizedTypeReference<T> parameterizedTypeReference) throws UnsupportedEncodingException, JsonProcessingException {
        String contentAsString = resultActions
                .andReturn()
                .getResponse()
                .getContentAsString();

        return objectMapper.readValue(
                objectMapper.readTree(contentAsString).path("contents").toString(),
                objectMapper.getTypeFactory().constructType(parameterizedTypeReference.getType())
        );
    }
}
