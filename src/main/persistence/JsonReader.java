package persistence;

import model.HotelList;
import model.Hotel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads hotel list from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads hotel list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public HotelList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseHotelList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses hotel list from JSON object and returns it
    private HotelList parseHotelList(JSONObject jsonObject) {
        HotelList hotelList = new HotelList();
        addHotels(hotelList, jsonObject);
        return hotelList;
    }

    // MODIFIES: hotelList
    // EFFECTS: parses thingies from JSON object and adds them to hotel list
    private void addHotels(HotelList hotelList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("hotels");
        for (Object json : jsonArray) {
            JSONObject nextHotel = (JSONObject) json;
            addHotel(hotelList, nextHotel);
        }
    }

    // MODIFIES: hotelList
    // EFFECTS: parses hotel from JSON object and adds it to hotel list
    private void addHotel(HotelList hotelList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int satisfaction = jsonObject.getInt("satisfaction");
        int price = jsonObject.getInt("price");
        Hotel hotel = new Hotel(price, satisfaction, name);
        hotelList.addHotel(hotel);
    }
}
