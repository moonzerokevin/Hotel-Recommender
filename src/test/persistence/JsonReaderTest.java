package persistence;

import model.Hotel;
import model.HotelList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            HotelList hotelList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyHotelList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyHotelList.json");
        try {
            HotelList hotelList = reader.read();
            assertEquals(0, hotelList.getList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralHotelList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralHotelList.json");
        try {
            HotelList hotelList = reader.read();
            ArrayList<Hotel> hotels = hotelList.getList();
            assertEquals(2, hotels.size());
            checkHotel(1, 1, "1", hotels.get(0));
            checkHotel(2, 2, "2", hotels.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}