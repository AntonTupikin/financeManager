package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseTest {
    String timestamp = ZonedDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    Purchase purchase = new Purchase("Молоко",300,timestamp);
    @Test
    void getYearTest() {
        String timestamp = ZonedDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy"));
        String[] lines = purchase.getPurchaseDate().split("\\.");
        Assertions.assertEquals("2023",lines[0]);
    }

    @Test
    void getDayTest() {
        String timestamp = ZonedDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd"));
        String[] lines = purchase.getPurchaseDate().split("\\.");
        Assertions.assertEquals(timestamp,lines[2]);
    }

    @Test
    void getMounthTest() {
        String timestamp = ZonedDateTime.now()
                .format(DateTimeFormatter.ofPattern("MM"));
        String[] lines = purchase.getPurchaseDate().split("\\.");
        Assertions.assertEquals(timestamp,lines[1]);
    }
}