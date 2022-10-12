package ui;

import model.Hotel;
import model.HotelList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

// Hotel recommender app
public class RecommenderAppScanner {
    private static final String JSON_STORE = "./data/hotels.json";
    private HotelList savHotel;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs hotel list and runs the recommender application
    public RecommenderAppScanner() throws FileNotFoundException {
        System.out.println("Welcome to Hotel recommending app");
        input = new Scanner(System.in);
        savHotel = new HotelList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runRecommender();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runRecommender() {
        displayMenu();
        int choice;
        choice = input.nextInt();
        while (choice != 0) {
            processChoice(choice);
            displayMenu();
            choice = input.nextInt();
        }
        quit();
        System.out.println("Thank you for using, goodbye!");
    }

    // EFFECTS: ask user to save their hotel list or not
    private void quit() {
        System.out.println("Do you want to save your hotels or not?");
        System.out.println("s -> quit with save");
        System.out.println("q -> quit without save");
        String choice = input.next();
        if (choice.equals("s")) {
            saveHotelList();
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user choice
    private void processChoice(int choice) {
        if (choice == 1) {
            addHotel();
        } else if (choice == 2) {
            deleteHotel();
        } else if (choice == 3) {
            viewList();
        } else if (choice == 4) {
            recommendWithPrice();
        } else if (choice == 5) {
            recommendWithSatisfaction();
        } else if (choice == 6) {
            loadHotelList();
        }
    }

    // EFFECTS: saves the hotel list to file
    private void saveHotelList() {
        try {
            jsonWriter.open();
            jsonWriter.write(savHotel);
            jsonWriter.close();
            System.out.println("Hotels Saved to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads hotel list from file
    private void loadHotelList() {
        try {
            savHotel = jsonReader.read();
            System.out.println("Hotels loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: adds a hotel with given price, satisfaction and name to the hotel choice list
    private void addHotel() {
        System.out.println("Please enter two integer and a String separately");
        System.out.println("First integer is price, second is satisfaction and String is name");
        int price;
        int satisfaction;
        String name;
        price = input.nextInt();
        satisfaction = input.nextInt();
        name = input.next();
        Hotel newHotel = new Hotel(price, satisfaction, name);
        savHotel.addHotel(newHotel);
    }

    //REQUIRES: 1 <= index <= the size of list
    //MODIFIES: this
    //EFFECTS: deletes the index hotel from the hotel choice list
    public void deleteHotel() {
        System.out.println("Please enter one integer");
        System.out.println("The index of deleted hotel");
        int index;
        index = input.nextInt();
        savHotel.deleteHotel(index);
    }

    //EFFECTS: prints the current hotels in the hotel choice list
    public void viewList() {
        ArrayList<Hotel> hotelList;
        hotelList = savHotel.getList();
        Iterator<Hotel> it = hotelList.iterator();
        int index = 0;
        while (it.hasNext()) {
            Hotel nowHotel = it.next();
            index++;
            System.out.println(index + ": Price:" + nowHotel.getPrice() + " Satisfaction:"
                    + nowHotel.getSatisfaction() + " Name:" + nowHotel.getName());
        }
    }

    //REQUIRES: 1 <= lowPrice <= 1000000 , 1 <= highPrice <= 1000000
    //EFFECTS: recommends the highest satisfaction hotel which has price between lowPrice and highPrice
    public void recommendWithPrice() {
        System.out.println("Please enter two integer separately");
        System.out.println("First is lowest price, second is highest price");
        int lowPrice;
        int highPrice;
        lowPrice = input.nextInt();
        highPrice = input.nextInt();
        if (!savHotel.checkPrice(lowPrice, highPrice)) {
            System.out.println("Sorry, there is no room price between " + lowPrice + " and " + highPrice);
        } else {
            System.out.println("I recommend you to book the room in "
                    + savHotel.recommendWithPrice(lowPrice, highPrice));
        }
    }

    //REQUIRES: 1 <= lowSatisfaction <= 5 , 1 <= highSatisfaction <= 5
    //EFFECTS: recommends the cheapest hotel which has satisfaction between lowSatisfaction and highSatisfaction
    public void recommendWithSatisfaction() {
        System.out.println("Please enter two integer separately");
        System.out.println("First is lowest satisfaction, second is highest satisfaction");
        int lowSatisfaction;
        int highSatisfaction;
        lowSatisfaction = input.nextInt();
        highSatisfaction = input.nextInt();
        if (!savHotel.checkSatisfaction(lowSatisfaction, highSatisfaction)) {
            System.out.println("Sorry, there is no room satisfaction between "
                    + lowSatisfaction + " and " + highSatisfaction);
        } else {
            System.out.println("I recommend you to book the room in "
                    + savHotel.recommendWithSatisfaction(lowSatisfaction, highSatisfaction));
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("Please enter the index of the operation you want to perform");
        System.out.println("    0: Quit this app");
        System.out.println("    1: Add a hotel to the choice list");
        System.out.println("    2: Delete a hotel from the choice list");
        System.out.println("    3: View current choice list of hotel");
        System.out.println("    4: Recommend a hotel in your price range");
        System.out.println("    5: Recommend a hotel in your satisfaction range");
        System.out.println("    6: Load your hotels saved last time");
    }
}
