package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Iterator;

//Represents a choice hotel list with a list of hotel.
public class HotelList implements Writable {
    private final ArrayList<Hotel> hotels;

    //EFFECTS: initialize hotel list
    public HotelList() {
        this.hotels = new ArrayList<>();
    }

    //REQUIRES: a hotel
    //MODIFIES: this
    //EFFECTS: add new hotel to the hotel choice list
    public void addHotel(Hotel newHotel) {
        Event event = new Event("Hotel name: " + newHotel.getName() + " has been added");
        EventLog.getInstance().logEvent(event);
        this.hotels.add(newHotel);
    }

    //REQUIRES: 1 <= index <= the size of list
    //MODIFIES: this
    //EFFECTS: delete the index hotel from the list
    public void deleteHotel(int index) {
        Event event = new Event("Hotel index: " + index + " has been deleted");
        EventLog.getInstance().logEvent(event);
        this.hotels.remove(index - 1);
    }

    //EFFECTS: return the hotel choice list
    public ArrayList<Hotel> getList() {
        return hotels;
    }

    //REQUIRES: 1 <= lowPrice <= 1000000 , 1 <= highPrice <= 1000000
    //EFFECTS: return true if there is a hotel's price is between lowPrice and highPrice.
    //         otherwise, return false
    public boolean checkPrice(int lowPrice, int highPrice) {
        boolean meetRequirement = false;
        Iterator<Hotel> it = hotels.iterator();
        while (it.hasNext()) {
            Hotel nowHotel = it.next();
            int nowPrice = nowHotel.getPrice();
            if ((nowPrice >= lowPrice) && (nowPrice <= highPrice)) {
                meetRequirement = true;
                break;
            }
        }
        return meetRequirement;
    }

    //REQUIRES: there is a hotel's price between lowPrice and highPrice
    //EFFECTS: recommend the highest satisfaction hotel which has price between lowPrice and highPrice
    public String recommendWithPrice(int lowPrice, int highPrice) {
        Hotel recommendHotel = new Hotel(1, 1, "1");
        int index = 0;
        int recommendIndex = 0;
        Iterator<Hotel> it = hotels.iterator();
        while (it.hasNext()) {
            Hotel nowHotel = it.next();
            index++;
            int nowPrice = nowHotel.getPrice();
            if ((nowPrice >= lowPrice) && (nowPrice <= highPrice)) {
                if (nowHotel.getSatisfaction() >= recommendHotel.getSatisfaction()) {
                    recommendHotel = nowHotel;
                    recommendIndex = index;
                }
            }
        }
        return (recommendIndex + ":" + recommendHotel.getName());
    }

    //REQUIRES: 1 <= lowSatisfaction <= 5, 1 <= highSatisfaction <= 5
    //EFFECTS: return true if there is a hotel's satisfaction is between lowSatisfaction and highSatisfaction
    //         otherwise false
    public boolean checkSatisfaction(int lowSatisfaction, int highSatisfaction) {
        boolean meetRequirement = false;
        Iterator<Hotel> it = hotels.iterator();
        while (it.hasNext()) {
            Hotel nowHotel = it.next();
            int nowSatisfaction = nowHotel.getSatisfaction();
            if ((nowSatisfaction >= lowSatisfaction) && (nowSatisfaction <= highSatisfaction)) {
                meetRequirement = true;
                break;
            }
        }
        return meetRequirement;
    }

    //REQUIRES: there is a hotel's satisfaction between lowSatisfaction and highSatisfaction
    //EFFECTS: recommend the cheapest hotel which has satisfaction between lowSatisfaction and highSatisfaction
    public String recommendWithSatisfaction(int lowSatisfaction, int highSatisfaction) {
        Hotel recommendHotel = new Hotel(1000000, 1, "1");
        int index = 0;
        int recommendIndex = 0;
        Iterator<Hotel> it = hotels.iterator();
        while (it.hasNext()) {
            Hotel nowHotel = it.next();
            index++;
            int nowSatisfaction = nowHotel.getSatisfaction();
            if ((nowSatisfaction >= lowSatisfaction) && (nowSatisfaction <= highSatisfaction)) {
                if (nowHotel.getPrice() <= recommendHotel.getPrice()) {
                    recommendIndex = index;
                    recommendHotel = nowHotel;
                }
            }
        }
        return (recommendIndex + ":" + recommendHotel.getName());
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("hotels", hotelsToJson());
        return json;
    }

    // EFFECTS: returns hotels in this HotelList as a JSON array
    private JSONArray hotelsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Hotel hotel : hotels) {
            jsonArray.put(hotel.toJson());
        }
        return jsonArray;
    }

    //
    public static void printLog() {
        Iterator iterator = EventLog.getInstance().iterator();

        while (iterator.hasNext()) {
            Event event = (Event)iterator.next();
            System.out.println(event.toString());
        }
    }

}
