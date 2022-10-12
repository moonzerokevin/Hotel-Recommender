package ui;

import model.Hotel;
import model.HotelList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class RecommenderApp {
    private static final String JSON_STORE = "./data/hotels.json";
    private static HotelList savHotel;
    private static JsonWriter jsonWriter;
    private static JsonReader jsonReader;

    public RecommenderApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        savHotel = new HotelList();
        new MainView();
    }

    //MODIFIES: this
    //EFFECTS: adds a hotel with given price, satisfaction and name to the hotel choice list
    public static void addHotel(Hotel newHotel) {
        savHotel.addHotel(newHotel);
    }

    //REQUIRES: 1 <= index <= the size of list
    //MODIFIES: this
    //EFFECTS: deletes the index hotel from the hotel choice list
    public static void deleteHotel(int index) {
        savHotel.deleteHotel(index);
    }

    //EFFECTS: prints the current hotels in the hotel choice list
    public static void viewList() {
        new ViewListView(savHotel);
    }

    //REQUIRES: 1 <= lowPrice <= 1000000 , 1 <= highPrice <= 1000000
    //EFFECTS: recommends the highest satisfaction hotel which has price between lowPrice and highPrice
    public static void recommendWithPrice(int lowPrice, int highPrice) {
        if (!savHotel.checkPrice(lowPrice, highPrice)) {
            new OutputView("Sorry, there is no room price between " + lowPrice + " and " + highPrice);
        } else {
            new OutputView("I recommend you to book the room in "
                    + savHotel.recommendWithPrice(lowPrice, highPrice));
        }
    }

    //REQUIRES: 1 <= lowSatisfaction <= 5 , 1 <= highSatisfaction <= 5
    //EFFECTS: recommends the cheapest hotel which has satisfaction between lowSatisfaction and highSatisfaction
    public static void recommendWithSatisfaction(int lowSatisfaction, int highSatisfaction) {
        if (!savHotel.checkSatisfaction(lowSatisfaction, highSatisfaction)) {
            new OutputView("Sorry, there is no room satisfaction between "
                    + lowSatisfaction + " and " + highSatisfaction);
        } else {
            new OutputView("I recommend you to book the room in "
                    + savHotel.recommendWithSatisfaction(lowSatisfaction, highSatisfaction));
        }
    }

    // EFFECTS: saves the hotel list to file
    public static void saveHotelList() {
        try {
            jsonWriter.open();
            jsonWriter.write(savHotel);
            jsonWriter.close();
            new OutputView("Hotels Saved to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            new OutputView("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads hotel list from file
    public static void loadHotelList() {
        try {
            savHotel = jsonReader.read();
            new OutputView("Hotels loaded from " + JSON_STORE);
        } catch (IOException e) {
            new OutputView("Unable to read from file: " + JSON_STORE);
        }
    }
}
