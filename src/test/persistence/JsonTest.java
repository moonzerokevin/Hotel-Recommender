package persistence;

import model.Hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkHotel(int price, int satisfaction, String name, Hotel hotel) {
        assertEquals(price, hotel.getPrice());
        assertEquals(satisfaction, hotel.getSatisfaction());
        assertEquals(name, hotel.getName());
    }
}
