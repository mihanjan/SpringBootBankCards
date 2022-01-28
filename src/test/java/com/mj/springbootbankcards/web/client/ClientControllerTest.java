package com.mj.springbootbankcards.web.client;

import com.mj.springbootbankcards.service.ClientService;
import com.mj.springbootbankcards.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClientControllerTest extends AbstractControllerTest {

    private static final String REST_URL = "/clients/";

    @Autowired
    private ClientService clientService;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
