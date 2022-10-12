package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class HotelListTest {
    HotelList hotels1;
    HotelList hotels2;
    Hotel hotel1;
    Hotel hotel2;
    Hotel hotel3;
    ArrayList<Hotel> hotels;

    @BeforeEach
    void pre() {
        hotel1 = new Hotel(200, 2, "hotel1");
        hotel2 = new Hotel(100, 1, "hotel2");
        hotel3 = new Hotel(300, 3, "hotel3");
        hotels1 = new HotelList();
        hotels2 = new HotelList();
        hotels = new ArrayList<>();
        hotels1.addHotel(hotel1);
        hotels2.addHotel(hotel1);
        hotels2.addHotel(hotel2);
    }

    @Test
    void getListTest() {
        hotels.add(hotel1);
        assertEquals(hotels, hotels1.getList());

        hotels.add(hotel2);
        assertEquals(hotels, hotels2.getList());
    }

    @Test
    void addHotelTest() {
        assertEquals(1 ,hotels1.getList().size());
        hotels1.addHotel(hotel2);
        assertEquals(2, hotels1.getList().size());

        assertEquals(2, hotels2.getList().size());
        hotels2.addHotel(hotel3);
        assertEquals(3, hotels2.getList().size());

        hotels1.deleteHotel(2);
        hotels2.deleteHotel(3);
    }

    @Test
    void deleteHotelTest() {
        assertEquals(2, hotels2.getList().size());
        hotels2.deleteHotel(2);
        assertEquals(1, hotels2.getList().size());

        assertEquals(1, hotels1.getList().size());
        hotels1.deleteHotel(1);
        assertEquals(0 ,hotels1.getList().size());

        hotels2.addHotel(hotel2);
        hotels1.addHotel(hotel1);
    }

    @Test
    void checkPriceTest() {
        assertFalse(hotels1.checkPrice(400, 500));
        assertTrue(hotels1.checkPrice(200, 200));
        assertFalse(hotels2.checkPrice(400, 500));
        assertTrue(hotels2.checkPrice(100, 100));
        assertTrue(hotels2.checkPrice(200, 200));
    }

    @Test
    void recommendWithPriceTest() {
        assertEquals("0:1", hotels1.recommendWithPrice(300, 500));
        assertEquals("1:hotel1", hotels1.recommendWithPrice(1,250));
        assertEquals("1:hotel1", hotels2.recommendWithPrice(1, 250));
        assertEquals("2:hotel2", hotels2.recommendWithPrice(1, 150));
        hotels2.addHotel(hotel3);
        assertEquals("3:hotel3", hotels2.recommendWithPrice(1, 310));
        hotels2.deleteHotel(3);
    }

    @Test
    void checkSatisfactionTest() {
        assertFalse(hotels1.checkSatisfaction(4, 5));
        assertTrue(hotels1.checkSatisfaction(2, 2));
        assertFalse(hotels2.checkSatisfaction(4, 5));
        assertTrue(hotels2.checkSatisfaction(1, 1));
        assertTrue(hotels2.checkSatisfaction(2, 2));
    }

    @Test
    void recommendWithSatisfactionTest() {
        assertEquals("0:1", hotels1.recommendWithSatisfaction(3, 5));
        assertEquals("1:hotel1", hotels1.recommendWithSatisfaction(1,2));
        assertEquals("2:hotel2", hotels2.recommendWithSatisfaction(1, 2));
        assertEquals("2:hotel2", hotels2.recommendWithSatisfaction(1, 1));
        hotels2.addHotel(hotel3);
        assertEquals("2:hotel2", hotels2.recommendWithSatisfaction(1, 3));
        hotels2.deleteHotel(2);
    }
}
