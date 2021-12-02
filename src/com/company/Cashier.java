
package com.company;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
public class Cashier {
    public static void main(String[] args) {
        ArrayList<Flights> flights;
        ArrayList<Tickets> tickets;
        Scanner in = new Scanner(System.in);                        //создавать/изменять/удалять данные таблицы билетов.
        try{
            Socket socket = new Socket("localhost",9090);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ClientHandler.db.connectToDB();

            System.out.println(ClientHandler.db.getAllTickets());

            while(true){
                System.out.println("""
                          ~Tickets~
                        [1] to create
                        [2] to edit
                        [3] to delete
                        """);
                int choice = in.nextInt();
                if(choice==1){
                    outputStream.writeObject("1");
                    outputStream.writeObject("tickets");
                    flights=(ArrayList<Flights>)inputStream.readObject();
                    for(Flights f:flights){
                        System.out.println(f);
                    }
                    System.out.println("insert flight id");
                    int id = in.nextInt();
                    System.out.println("name");
                    String name = in.next();
                    System.out.println("surname");
                    String surname = in.next();
                    System.out.println("passport num");
                    String passport = in.next();
                    System.out.println("Ticket type");
                    String ticket = in.next();
                    Tickets t = new Tickets(null,id,name,surname,passport,ticket);
                    outputStream.writeObject(t);
                    System.out.println("added but not sure");
                }else if(choice==2){
                    outputStream.writeObject("2");
                    outputStream.writeObject("tickets");
                    tickets=(ArrayList<Tickets>)inputStream.readObject();
                    for(Tickets t:tickets){
                        System.out.println(t);
                    }
                    System.out.println("insert id of ticket u want to edit");
                    Long id = in.nextLong();
                    flights=(ArrayList<Flights>) inputStream.readObject();
                    for(Flights f:flights){
                        System.out.println(f);
                    }
                    System.out.println("insert flight id");
                    int id_flight = in.nextInt();
                    System.out.println("name");
                    String name = in.next();
                    System.out.println("surname");
                    String surname = in.next();
                    System.out.println("passport num");
                    String passport = in.next();
                    System.out.println("Ticket type");
                    String ticket = in.next();
                    Tickets t = new Tickets(id,id_flight,name,surname,passport,ticket);
                    outputStream.writeObject(t);
                }else if(choice==3){
                    outputStream.writeObject("3");
                    outputStream.writeObject("tickets");
                    tickets=(ArrayList<Tickets>) inputStream.readObject();
                    for(Tickets t:tickets){
                        System.out.println(t);
                    }
                    System.out.println("insert id of ticket u want to delete");
                    Long id = in.nextLong();
                    outputStream.writeObject(id);
                    System.out.println("successfully deleted , (i guess)");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

