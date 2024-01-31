package com.bananaapps.bananamusic.controller;

import com.bananaapps.bananamusic.domain.StatusMessage;
import com.bananaapps.bananamusic.domain.music.PurchaseOrder;
import com.bananaapps.bananamusic.domain.music.PurchaseOrderLineSong;
import com.bananaapps.bananamusic.domain.music.Song;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@Sql(value = "classpath:testing.sql")
//@Sql(value = "classpath:testing_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles({"dev"})
class ShoppingServiceControllerTest {

    @Autowired
    ShoppingServiceController controller;

    @Test
    @Order(1)
    void getBalance_0() {
        double bal = controller.getBalance();
        assertThat(bal).isEqualTo(0);
    }

    @Test
    @Order(2)
    void getItemCount_0() {
        int count = controller.getItemCount();
        assertThat(count).isEqualTo(0);
    }

    @Test
    @Order(3)
    void addItem() {
        PurchaseOrderLineSong line = new PurchaseOrderLineSong(null, null, new Song(1l), 1, 10.0);
        ResponseEntity<PurchaseOrderLineSong> response = controller.addItem(line);

        assertThat(response.getStatusCode().value())
                .isEqualTo(HttpStatus.ACCEPTED.value());

        double bal = controller.getBalance();
        assertThat(bal).isGreaterThan(0);

        int count = controller.getItemCount();
        assertThat(count).isGreaterThan(0);
    }

    @Test
    @Order(4)
    void removeItem() {
        PurchaseOrderLineSong line = new PurchaseOrderLineSong(null, null, new Song(1l), 1, 10.0);
        controller.addItem(line);

        Long itemToRemove = 1L;
        ResponseEntity<StatusMessage> response = controller.removeItem(itemToRemove);

        assertThat(response.getStatusCode().value())
                .isEqualTo(HttpStatus.ACCEPTED.value());

        double bal = controller.getBalance();
        assertThat(bal).isEqualTo(0);

        int count = controller.getItemCount();
        assertThat(count).isEqualTo(0);
    }


    @Test
    @Order(8)
    void empty() {
        PurchaseOrderLineSong line1 = new PurchaseOrderLineSong(null, null, new Song(1l), 1, 10.0);
        controller.addItem(line1);
        PurchaseOrderLineSong line2 = new PurchaseOrderLineSong(null, null, new Song(2l), 1, 10.0);
        controller.addItem(line2);

        ResponseEntity<StatusMessage> response = controller.empty();
        assertThat(response.getStatusCode().value())
                .isEqualTo(HttpStatus.ACCEPTED.value());

        double bal = controller.getBalance();
        assertThat(bal).isEqualTo(0);

        int count = controller.getItemCount();
        assertThat(count).isEqualTo(0);
    }

    @Test
    @Order(8)
    void buy() {
        PurchaseOrderLineSong line1 = new PurchaseOrderLineSong(null, null, new Song(1l), 1, 10.0);
        controller.addItem(line1);
        PurchaseOrderLineSong line2 = new PurchaseOrderLineSong(null, null, new Song(2l), 1, 10.0);
        controller.addItem(line2);

        ResponseEntity<StatusMessage> response = controller.buy();
        assertThat(response.getStatusCode().value())
                .isEqualTo(HttpStatus.ACCEPTED.value());

        double bal = controller.getBalance();
        assertThat(bal).isEqualTo(0);

        int count = controller.getItemCount();
        assertThat(count).isEqualTo(0);
    }
}