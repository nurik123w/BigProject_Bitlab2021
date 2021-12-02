package com.company;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
public class DataBase {
 Connection connect;
Flights flights;
    public void connectToDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2?useUnicode=true&serverTimezone=UTC","root","");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Cities getCityById(Long id1){
        Cities city = new Cities();
        try{
            PreparedStatement ps = connect.prepareStatement("SELECT * FROM cities WHERE id=?");
            ps.setLong(1,id1);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String country = rs.getString("country");
                String short_name = rs.getString("short_name");
                city.setId(id);
                city.setName(name);
                city.setCountry(country);
                city.setShort_name(short_name);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return city;
    }

    public Aircraft getAircraftById(Long id1){
        Aircraft aircraft= new Aircraft();
        try{
            PreparedStatement ps = connect.prepareStatement("SELECT * FROM aircrafts WHERE id=?");
                ps.setLong(1,id1);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String model = rs.getString("model");
                int Bcapacity = rs.getInt("business_class_capacity");
                int Ecapacity = rs.getInt("econom_class_capacity");
                aircraft.setId(id);
                aircraft.setName(name);
                aircraft.setModel(model);
                aircraft.setBusiness_class_capacity(Bcapacity);
                aircraft.setEconom_class_capacity(Ecapacity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return aircraft;
    }
    public ArrayList<Aircraft> getAllAircraft(){
        ArrayList<Aircraft> aircraft = new ArrayList<>();
        try{
            PreparedStatement ps = connect.prepareStatement("SELECT * FROM aircrafts");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String model = rs.getString("model");
                int Bcapacity = rs.getInt("business_class_capacity");
                int Ecapacity = rs.getInt("econom_class_capacity");
                aircraft.add(new Aircraft(id,name,model,Bcapacity,Ecapacity));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return aircraft;
    }

    public void addAirplane(Aircraft a){
        try{
            PreparedStatement pt = connect.prepareStatement("INSERT INTO aircrafts (name,model,business_class_capacity,econom_class_capacity)values(?,?,?,?)");
            pt.setString(1,a.getName());
            pt.setString(2,a.getModel());
            pt.setInt(3,a.getBusiness_class_capacity());
            pt.setInt(4,a.getEconom_class_capacity());
            pt.executeUpdate();
            pt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public  void deleteAirplane(Long id){
        try{
            PreparedStatement pt = connect.prepareStatement("DELETE FROM aircrafts where id=?");
            pt.setLong(1,id);
            pt.executeUpdate();
            pt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public  void editAircraft(Aircraft a){
        try{
            PreparedStatement pt=connect.prepareStatement("UPDATE aircrafts set name=?,model=?,business_class_capacity=?,econom_class_capacity=? where id=? ");
            pt.setString(1,a.getName());
            pt.setString(2, a.getModel());
            pt.setInt(3,a.getBusiness_class_capacity());
            pt.setInt(4,a.getEconom_class_capacity());
            pt.setLong(5,a.getId());
            pt.executeUpdate();
            pt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public  ArrayList<Cities> getAllCities(){
        ArrayList<Cities> cities = new ArrayList<>();
        try{
            PreparedStatement ps = connect.prepareStatement("SELECT * FROM cities");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String country = rs.getString("country");
                String shortName = rs.getString("short_name");
                cities.add(new Cities(id,name,country,shortName));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return cities;
    }
    public void addCity(Cities a){
        try{
            PreparedStatement pt = connect.prepareStatement("INSERT INTO cities(name,country,short_name)values(?,?,?)");
            pt.setString(1,a.getName());
            pt.setString(2,a.getCountry());
            pt.setString(3,a.getShort_name());
            pt.executeUpdate();
            pt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deleteCity(Long id){
        try{
            PreparedStatement pt = connect.prepareStatement("DELETE FROM cities where id=?");
            pt.setLong(1,id);
            pt.executeUpdate();
            pt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void editDeCity(Cities city){
        try{
            PreparedStatement pt =connect.prepareStatement("UPDATE cities set name=?,country=?,short_name=? where id=?");
            pt.setString(1,city.getName());
            pt.setString(2,city.getCountry());
            pt.setString(3, city.getShort_name());
            pt.setLong(4,city.getId());
            pt.executeUpdate();
            pt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Flights> getAllFlights(){
        ArrayList<Flights>f = new ArrayList<>();
        try{
            PreparedStatement ps = connect.prepareStatement("SELECT * FROM flights");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Long id = rs.getLong("id");
                int plane_id = rs.getInt("aircraft_id");
                int departure_city_id = rs.getInt("departure_city_id");
                int arrival_city_id = rs.getInt("arrival_city_id");
                String departure_time = rs.getString("departure_time");
                int econom_place_price = rs.getInt("econom_place_price");
                int business_place_price = rs.getInt("business_place_price");
                String date = rs.getString("date");
                f.add(new Flights(id,getAircraftById((long)plane_id),getCityById((long)departure_city_id),getCityById((long)arrival_city_id),departure_time,econom_place_price,business_place_price,date));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }

    public void addFlight(Flights a){
        try{
            PreparedStatement pt = connect.prepareStatement("INSERT INTO flights(aircraft_id,departure_city_id,arrival_city_id,departure_time,econom_place_price,business_place_price,date)values(?,?,?,?,?,?,?)");
            pt.setInt(1,a.getAircraft_id());
            pt.setInt(2,a.getDeparture_city_id());
            pt.setInt(3,a.getArrival_city_id());
            pt.setString(4,a.getDeparture_time());
            pt.setInt(5,a.getEconom_place_price());
            pt.setInt(6,a.getBusiness_place_price());
            pt.setString(7,a.getDate());
            pt.executeUpdate();
            pt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteFlights(Long id){
        try{
            PreparedStatement pt =connect.prepareStatement("DELETE FROM flights where id=?");
            pt.setLong(1,id);
            pt.executeUpdate();
            pt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void editFLights(Flights a){
        try{
            PreparedStatement pt =connect.prepareStatement("UPDATE flights set aircraft_id=?,departure_city_id=?,arrival_city_id=?,departure_time=?,econom_place_price=?,business_place_price=? where id=? ");
            pt.setInt(1,a.getAircraft_id());
            pt.setInt(2,a.getDeparture_city_id());
            pt.setInt(3,a.getArrival_city_id());
            pt.setString(4,a.getDeparture_time());
            pt.setInt(5,a.getEconom_place_price());
            pt.setInt(6,a.getBusiness_place_price());
            pt.setLong(7,a.getId());

            pt.executeUpdate();
            pt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<Flights> getFlightsByDate(String date){
        ArrayList<Flights> fs = new ArrayList<>();
        try{
            PreparedStatement ps = connect.prepareStatement("SELECT * FROM flights WHERE date=?");
            ps.setString(1,date);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                long id = rs.getLong("id");
                long airId=rs.getInt("aircraft_id");
                long depId =rs.getInt("departure_city_id");
                long arrId=rs.getInt("arrival_city_id");
                String deptime=rs.getString("departure_time");
                int ecPrice=rs.getInt("econom_place_price");
                int buPrice=rs.getInt("business_place_price");
                fs.add(new Flights(id,getAircraftById(airId),getCityById(depId),getCityById(arrId),deptime,ecPrice,buPrice,date));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return fs;
    }

    public Flights getFlightById(Long id1){
        try{
            PreparedStatement ps = connect.prepareStatement("SELECT * FROM flights WHERE id=?");
            ps.setLong(1,id1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                long airId=rs.getInt("aircraft_id");
                long depId =rs.getInt("departure_city_id");
                long arrId=rs.getInt("arrival_city_id");
                String deptime=rs.getString("departure_time");
                int ecPrice=rs.getInt("econom_place_price");
                int buPrice=rs.getInt("business_place_price");
                String date = rs.getString("date");
                flights = new Flights(id1,getAircraftById(airId),getCityById(depId),getCityById(arrId),deptime,ecPrice,buPrice,date);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flights;
    }

    public ArrayList<Tickets> getAllTickets(){
        ArrayList<Tickets> tickets = new ArrayList<>();
        try{
            PreparedStatement pt = connect.prepareStatement("SELECT * FROM tickets");
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                Long id = rs.getLong("id");
                int flight_id = rs.getInt("flight_id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String passport_number = rs.getString("passport_number");
                String ticket_type = rs.getString("ticket_type");
                tickets.add(new Tickets(id,getFlightById((long) flight_id),name,surname,passport_number,ticket_type));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return tickets;
    }
    public void addTicket(Tickets t){
        try{
            PreparedStatement pt = connect.prepareStatement("INSERT INTO tickets(flight_id,name,surname,passport_number,ticket_type)values(?,?,?,?,?)");
            pt.setInt(1,t.getFlight_id());
            pt.setString(2,t.getName());
            pt.setString(3,t.getSurname());
            pt.setString(4,t.getPassport_number());
            pt.setString(5,t.getTicket_type());
            pt.executeUpdate();
            pt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void editDeTicket(Tickets t){
        try{
            PreparedStatement pt = connect.prepareStatement("UPDATE tickets set flight_id=?,name=?,surname=?,passport_number=?,ticket_type=? where id=?");
            pt.setInt(1,t.getFlight_id());
            pt.setString(2,t.getName());
            pt.setString(3,t.getSurname());
            pt.setString(4,t.getPassport_number());
            pt.setString(5,t.getTicket_type());
            pt.setLong(6,t.getId());
            pt.executeUpdate();
            pt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deleteTicket(Long id){
        try{
            PreparedStatement pt=connect.prepareStatement("DELETE FROM tickets where id=?");
            pt.setLong(1,id);
            pt.executeUpdate();
            pt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
