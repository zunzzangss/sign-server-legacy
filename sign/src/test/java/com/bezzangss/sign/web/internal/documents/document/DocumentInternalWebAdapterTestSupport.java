package com.bezzangss.sign.web.internal.documents.document;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.MultiValueMap;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DocumentInternalWebAdapterTestSupport {
    public static ResultActions findById(MockMvc mockMvc, HttpHeaders httpHeaders, String id, MultiValueMap<String, String> params) throws Exception {
        return mockMvc.perform(
                        get("/internal/v1/document/{id}", id)
                                .params(params)
                                .headers(httpHeaders)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print());
    }
}
