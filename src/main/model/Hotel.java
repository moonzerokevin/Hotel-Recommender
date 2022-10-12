package model;

import org.json.JSONObject;
import persistence.Writable;

import java.sql.Wrapper;

//Represents a hotel having a price, a satisfaction and a name.
public class Hotel implements Writable {
    private final int price;
    private final int satisfaction;
    private final String name;

    // REQUIRES: 1 <= initialPrice <= 1000000 , 1 <= initialSatisfaction <= 5
    // EFFECTS: constructs hotel with given initial price and initial satisfaction from 0 to 5 and hotel name.
    public Hotel(int initialPrice, int initialSatisfaction, String hotelName) {
        this.price = initialPrice;
        this.satisfaction = initialSatisfaction;
        this.name = hotelName;
    }

    // EFFECTS: returns the price
    public int getPrice() {
        return this.price;
    }

    // EFFECTS: returns the satisfaction
    public int getSatisfaction() {
        return this.satisfaction;
    }

    // EFFECTS: returns the name
    public String getName() {
        return this.name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("price", price);
        json.put("satisfaction", satisfaction);
        return json;
    }
}
