package com.bananaapps.bananamusic.controller;

import com.bananaapps.bananamusic.domain.auth.AuthRequest;
import com.bananaapps.bananamusic.domain.auth.AuthResponse;
import com.bananaapps.bananamusic.domain.user.ERole;
import com.bananaapps.bananamusic.domain.user.User;
import com.bananaapps.bananamusic.persistence.UserRepository;
import com.bananaapps.bananamusic.persistence.music.SongRepository;
import com.bananaapps.bananamusic.util.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthServiceTest {

    Logger logger = LoggerFactory.getLogger(AuthServiceTest.class);

    String accessToken = null;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    void given_existing_user_when_login_success() throws Exception {
        // given
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String email = "r@r.com";
        String password = "pa55wrd";
        String enc_password = passwordEncoder.encode(password);

        User aUser = new User(null, email, enc_password, ERole.USER);
        userRepository.save(aUser);

        logger.info("Saved user:" + aUser);

        // when
        AuthRequest authRequest = new AuthRequest(email, password);

        //then
        MvcResult result = mvc.perform(post("/auth/login")
                        .content(JsonUtil.asJsonString(authRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();
        AuthResponse response = new ObjectMapper().readValue(contentAsString, AuthResponse.class);

        logger.info(response.toString());

        accessToken = response.getAccessToken();
    }

    @Test
    @Order(2)
    void given_accesstoken_when_balance_then_success() {

        try {
            // given: existing token
            // given_existing_user_when_login_success(); // must  have executed previously in test sequence

            // when
            mvc.perform(get("/cart/balance")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header("Authorization", "Bearer " + accessToken)
                    )
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", is(equalTo(0.0))));

        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

}