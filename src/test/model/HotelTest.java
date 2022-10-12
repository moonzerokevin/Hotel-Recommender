package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class HotelTest {
    Hotel hotel1;
    Hotel hotel2;
    @BeforeEach
    void pre() {
        hotel1 = new Hotel(100, 3, "hotel1");
        hotel2 = new Hotel(200, 4, "hotel2");
    }

    @Test
    void getPriceTest() {
        assertEquals(100, hotel1.getPrice());
        assertEquals(200, hotel2.getPrice());
    }

    @Test
    void getSatisfactionTest() {
        assertEquals(3, hotel1.getSatisfaction());
        assertEquals(4, hotel2.getSatisfaction());
    }

    @Test
    void getNameTest() {
        assertEquals("hotel1", hotel1.getName());
        assertEquals("hotel2", hotel2.getName());
    }
}