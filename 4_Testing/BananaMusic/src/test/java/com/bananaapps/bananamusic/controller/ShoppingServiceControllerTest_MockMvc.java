package com.bananaapps.bananamusic.controller;

import com.bananaapps.bananamusic.domain.music.PurchaseOrderLineSong;
import com.bananaapps.bananamusic.domain.music.Song;
import com.bananaapps.bananamusic.service.music.ShoppingCart;
import com.bananaapps.bananamusic.util.JsonUtil;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
//@Sql(value = "classpath:testing.sql")
//@Sql(value = "classpath:testing_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles({"dev"})
class ShoppingServiceControllerTest_MockMvc {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ShoppingServiceController controller;

    @Autowired
    private ShoppingCart service;

    @Test
    @Order(1)
    void getBalance_0() throws Exception {
        mvc.perform(get("/cart/balance").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(equalTo(0.0))));

    }


    @Test
    @Order(3)
    void addItem() throws Exception {
        PurchaseOrderLineSong line = new PurchaseOrderLineSong(null, null, new Song(1l), 1, 10.0);

        mvc.perform(put("/cart")
                        .content(JsonUtil.asJsonString(line))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.message", is(equalTo("Added"))));

    }

    @Test
    @Order(8)
    void buy() throws Exception {
        PurchaseOrderLineSong line1 = new PurchaseOrderLineSong(null, null, new Song(1l), 1, 10.0);
        service.addItem(line1);
        PurchaseOrderLineSong line2 = new PurchaseOrderLineSong(null, null, new Song(2l), 1, 10.0);
        service.addItem(line2);

        mvc.perform(post("/cart/buy")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.message", is(equalTo("Saved"))));
    }
}