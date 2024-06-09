package com.ads.gynvagas.jobboard.controller;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.config.http.MatcherType.mvc;

class ResourceControllerTest {
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    public void whenAnonymousAccessLogin_thenOk() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    public void whenAnonymousAccessRestrictedEndpoint_thenIsUnauthorized() throws Exception {
        mockMvc.perform(get("/all"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails()
    public void whenUserAccessUserSecuredEndpoint_thenOk() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails()
    public void whenUserAccessRestrictedEndpoint_thenOk() throws Exception {
        mockMvc.perform(get("/all"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails()
    public void whenUserAccessAdminSecuredEndpoint_thenIsForbidden() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails()
    public void whenUserAccessDeleteSecuredEndpoint_thenIsForbidden() throws Exception {
        mockMvc.perform((RequestBuilder) delete("/delete"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void whenAdminAccessUserEndpoint_thenOk() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void whenAdminAccessAdminSecuredEndpoint_thenIsOk() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk());
    }

//    @Test
//    @WithUserDetails(value = "admin")
//    public void whenAdminAccessDeleteSecuredEndpoint_thenIsOk() throws Exception {
//        mockMvc.perform(delete("/delete").content("{}"))
//                .andExpect(status().isOk());
//    }

}