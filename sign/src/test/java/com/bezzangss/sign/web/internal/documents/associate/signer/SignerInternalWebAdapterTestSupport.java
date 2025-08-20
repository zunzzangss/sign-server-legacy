package com.bezzangss.sign.web.internal.documents.associate.signer;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.MultiValueMap;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class SignerInternalWebAdapterTestSupport {
    public static ResultActions sign(MockMvc mockMvc, HttpHeaders httpHeaders, String id) throws Exception {
        return mockMvc.perform(
                        post("/internal/v1/signer/{id}/sign", id)
                                .headers(httpHeaders)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print());
    }

    public static ResultActions findAllByDocumentId(MockMvc mockMvc, HttpHeaders httpHeaders, MultiValueMap<String, String> params) throws Exception {
        return mockMvc.perform(
                        get("/internal/v1/signer/find-all-by-document-id")
                                .params(params)
                                .headers(httpHeaders)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print());
    }
}
