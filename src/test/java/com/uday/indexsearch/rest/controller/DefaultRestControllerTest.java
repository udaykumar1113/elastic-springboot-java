package com.uday.indexsearch.rest.controller;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestHeader;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DefaultRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void headerByAnnotation(@RequestHeader(name="User-Agent") String userAgent) throws Exception {

        String endpoint="/api/header-one";
        MockHttpServletRequestBuilder requestBuilders=MockMvcRequestBuilders.get(endpoint);

        mockMvc.perform(requestBuilders).andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Postman")));
    }
}
