package com.company;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Flights  implements Serializable {
    Long id;
    int aircraft_id;
    int departure_city_id;
    int arrival_city_id;
    String departure_time ;
    String date;
    int econom_place_price;
    int business_place_price;
    Aircraft aircraft;
    Cities arrivalCity;
    Cities departure;
  

    public Flights(Long id,Aircraft aircraft,Cities departure,Cities arrivalCity, String departure_time, int econom_place_price, int business_place_price,String date){
        this.id=id;
        this.aircraft=aircraft;
        this.departure=departure;
        this.arrivalCity=arrivalCity;
        this.departure_time=departure_time;
        this.econom_place_price=econom_place_price;
        this.business_place_price=business_place_price;
        this.date=date;
    }

    public String getDate() {
        return date;
    }

    public Flights(String date){
        this.date=date;
    }
    public Flights(Long id, int aircraft_id, int departure_city_id, int arrival_city_id, String departure_time, int econom_place_price, int business_place_price, String date){
        this.id = id;
        this.aircraft_id = aircraft_id;
        this.departure_city_id = departure_city_id;
        this.arrival_city_id = arrival_city_id;
        this.departure_time = departure_time;
        this.econom_place_price = econom_place_price;
        this.business_place_price = business_place_price;
        this.date=date;
    }
    public Long getId() {
        return id;
    }
    public int getAircraft_id() {
        return aircraft_id;
    }
    public int getDeparture_city_id() {
        return departure_city_id;
    }
    public int getArrival_city_id() {
        return arrival_city_id;
    }
    public String getDeparture_time() {
        return departure_time;
    }
    public int getEconom_place_price() {
        return econom_place_price;
    }
    public int getBusiness_place_price() {
        return business_place_price;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public Cities getArrivalCity() {
        return arrivalCity;
    }

    public Cities getDeparture() {
        return departure;
    }

    public String toString(){
        return "Flights id: "+id+"\n"+aircraft+"\n"+" depature city: "+departure+"\n"+" arrival city: "+arrivalCity+
                "\n"+" depature time: "+departure_time+" econom price: "+econom_place_price+" business price: "+business_place_price+" "+date;
    }
    public String getForTable(){
        return departure.getName()+"~"+arrivalCity.getName();
    }
}
