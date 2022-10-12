package persistence;

import model.Hotel;
import model.HotelList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            HotelList hotelList = new HotelList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyHotelList() {
        try {
            HotelList hotelList = new HotelList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyHotelList.json");
            writer.open();
            writer.write(hotelList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyHotelList.json");
            hotelList = reader.read();
            assertEquals(0, hotelList.getList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralHotelList() {
        try {
            HotelList hotelList = new HotelList();
            hotelList.addHotel(new Hotel(1, 1, "1"));
            hotelList.addHotel(new Hotel(2, 2, "2"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralHotelList.json");
            writer.open();
            writer.write(hotelList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralHotelList.json");
            hotelList = reader.read();
            ArrayList<Hotel> hotels = hotelList.getList();
            assertEquals(2, hotels.size());
            checkHotel(1, 1, "1", hotels.get(0));
            checkHotel(2, 2, "2", hotels.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}