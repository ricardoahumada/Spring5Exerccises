package com.bananaapps.bananamusic.controller;

import com.bananaapps.bananamusic.domain.StatusMessage;
import com.bananaapps.bananamusic.domain.music.PurchaseOrderLineSong;
import com.bananaapps.bananamusic.domain.music.Song;
import com.bananaapps.bananamusic.service.music.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql(value = "classpath:testing.sql")
//@Sql(value = "classpath:testing_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles({"dev"})
class ShoppingServiceControllerTest_RestTemplate {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        Mockito.when(service.getBalance())
                .thenReturn(0.0);

        Mockito.doNothing().when(service).addItem(Mockito.any(PurchaseOrderLineSong.class));

        Mockito.doNothing().when(service).buy();
    }
    @MockBean
    private ShoppingCart service;

    @Test
    @Order(1)
    void getBalance_0() throws Exception {
        ResponseEntity<Double> response = restTemplate
                .getForEntity("http://localhost:" + port + "/cart/balance",
                        Double.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(0.0);
    }


    @Test
    @Order(3)
    void addItem() throws Exception {
        PurchaseOrderLineSong line = new PurchaseOrderLineSong(null, null, new Song(1l), 1, 10.0);

        final String baseUrl = "http://localhost:" + port + "/cart";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("ACCEPT", "application/json");

        HttpEntity<PurchaseOrderLineSong> request = new HttpEntity<>(line, headers);

        ResponseEntity<StatusMessage> response = this.restTemplate.exchange(uri, HttpMethod.PUT, request, StatusMessage.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(response.getBody().getMessage()).isEqualTo("Added");
    }

    @Test
    @Order(8)
    void buy() throws Exception {
        PurchaseOrderLineSong line1 = new PurchaseOrderLineSong(null, null, new Song(1l), 1, 10.0);
        service.addItem(line1);
        PurchaseOrderLineSong line2 = new PurchaseOrderLineSong(null, null, new Song(2l), 1, 10.0);
        service.addItem(line2);

        final String baseUrl = "http://localhost:" + port + "/cart/buy";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("ACCEPT", "application/json");

        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<StatusMessage> response = this.restTemplate.exchange(uri, HttpMethod.POST, request, StatusMessage.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(response.getBody().getMessage()).isEqualTo("Saved");
    }
}