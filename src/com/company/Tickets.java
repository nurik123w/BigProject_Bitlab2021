package com.company;
import java.io.Serializable;
import java.util.ArrayList;

public class Tickets implements Serializable {
    Long id;
    int flight_id;
    String name;
    String surname;
    String passport_number;
    String ticket_type;
    Flights  flights;


    public Tickets(Long id , Flights flights, String name, String surname, String passport_number, String ticket_type){
        this.id = id;
        this.flights=flights;
        this.name = name;
        this.surname = surname;
        this.passport_number = passport_number;
        this.ticket_type = ticket_type;
    }

    public Flights  getFlights() {
        return flights;
    }

    public Tickets(Long id, int flight_id, String name, String surname, String passport_number, String ticket_type) {
        this.id = id;
        this.flight_id = flight_id;
        this.name = name;
        this.surname = surname;
        this.passport_number = passport_number;
        this.ticket_type = ticket_type;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getFlight_id() {
        return flight_id;
    }
    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getPassport_number() {
        return passport_number;
    }
    public void setPassport_number(String passport_number) {
        this.passport_number = passport_number;
    }
    public String getTicket_type() {
        return ticket_type;
    }
    public void setTicket_type(String ticket_type) {
        this.ticket_type = ticket_type;
    }
    @Override
    public String toString() {
        return "Tickets: " +
                "id: " + id +" "+flights+
                ", name: " + name +
                ", surname: " + surname +
                ", passport_number: " + passport_number +
                ", ticket_type: " + ticket_type;
    }
}
