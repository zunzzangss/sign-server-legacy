package com.bezzangss.sign.web.internal.resources.resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class ResourceInternalWebRestDoc {
    public static ResultActions requestCreateByFile(MockMvc mockMvc, HttpHeaders httpHeaders, MockMultipartFile file) throws Exception {
        return mockMvc.perform(
                        fileUpload("/internal/v1/resource/create-by-file")
                                .file(file)
                                .headers(httpHeaders)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print());
    }

    public static MockMultipartFile getMockMultipartFileSuccess() {
        return new MockMultipartFile(
                "file",
                UUID.randomUUID().toString(),
                MediaType.TEXT_PLAIN_VALUE,
                UUID.randomUUID().toString().getBytes()
        );
    }
}
