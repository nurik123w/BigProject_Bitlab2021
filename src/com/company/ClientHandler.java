package com.company;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends  Thread{
    static DataBase db = new DataBase();
    Socket socket;
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;
    public ClientHandler(Socket socket) throws IOException {
        this.socket=socket;
        inputStream = new ObjectInputStream(socket.getInputStream());
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        db.connectToDB();
    }

    public void run() {
        try {
            while (true) {
                String request = (String) inputStream.readObject();
                if (request.equals("ADD_CITY")) {
                    Cities c;
                        db.addCity(c=(Cities)inputStream.readObject());
                } else if (request.equals("ADD_PLANE")) {
                    db.addAirplane((Aircraft) inputStream.readObject());
                } else if (request.equals("ADD_FLIGHTS")) {
                    Flights f=(Flights) inputStream.readObject();
                    db.addFlight(f);
                } else if (request.equals("ADD_TICKETS")) {
                    db.addTicket((Tickets) inputStream.readObject());
                    System.out.println("added");
                } else if (request.equals("EDIT_CITY")) {
                    Cities c = (Cities) inputStream.readObject();
                    db.editDeCity(c);
                } else if (request.equals("EDIT_AIRCRAFT")) {
                    Aircraft a = (Aircraft) inputStream.readObject();
                    db.editAircraft(a);
                } else if (request.equals("EDIT_FLIGHTS")) {
                    db.editFLights((Flights) inputStream.readObject());
                } else if (request.equals("EDIT_TICKETS")) {
                    db.editDeTicket((Tickets) inputStream.readObject());
                } else if (request.equals("DELETE_CITY")) {
                    db.deleteCity((long) inputStream.readObject());
                } else if (request.equals("DELETE_AIRCRAFT")) {
                    db.deleteAirplane((long) inputStream.readObject());
                } else if (request.equals("DELETE_FLIGHTS")) {
                    db.deleteFlights((Long) inputStream.readObject());
                } else if (request.equals("DELETE_TICKETS")) {
                    db.deleteTicket((long) inputStream.readObject());
                } else if (request.equals("GET_ALL_AIRCRAFTS")) {
                    outputStream.writeObject(db.getAllAircraft());
                } else if (request.equals("GET_ALL_CITIES")){
                    outputStream.writeObject(db.getAllCities());
                } else if (request.equals("GET_ALL_FLIGHTS")){
                    outputStream.writeObject(db.getAllFlights());
                } else if(request.equals("GET_ALL_TICKETS")){
                    outputStream.writeObject(db.getAllTickets());
                } else if (request.equals("GET_FLIGHT_BY_ID")){
                   int id = (int) inputStream.readObject();
                    outputStream.writeObject(db.getFlightById((long) id));
                } else if(request.equals("GET")){
                    String date = (String) inputStream.readObject();
                    outputStream.writeObject(db.getFlightsByDate(date));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
