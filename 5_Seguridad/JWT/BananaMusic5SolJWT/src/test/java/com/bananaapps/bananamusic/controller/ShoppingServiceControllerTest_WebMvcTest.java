package com.bananaapps.bananamusic.controller;

import com.bananaapps.bananamusic.domain.music.PurchaseOrder;
import com.bananaapps.bananamusic.domain.music.PurchaseOrderLineSong;
import com.bananaapps.bananamusic.domain.music.Song;
import com.bananaapps.bananamusic.service.music.ShoppingCart;
import com.bananaapps.bananamusic.util.JsonUtil;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ShoppingServiceController.class)
//@Sql(value = "classpath:testing.sql")
//@Sql(value = "classpath:testing_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles({"dev"})
class ShoppingServiceControllerTest_WebMvcTest {

    @BeforeEach
    public void setUp() {
        Mockito.when(service.getBalance())
                .thenReturn(0.0);

        Mockito.doNothing().when(service).addItem(Mockito.any(PurchaseOrderLineSong.class));

        Mockito.doNothing().when(service).buy();
    }

    @Autowired
    private MockMvc mvc;

    @Autowired
    ShoppingServiceController controller;

    @MockBean
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