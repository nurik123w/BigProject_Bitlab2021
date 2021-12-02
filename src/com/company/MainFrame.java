
package com.company;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainFrame extends JFrame {
    ToolKit toolKit;
    PlaneCreate planeCreate;
    CityCreate cityCreate;
    FlightsCreate flightsCreate;
    ConfirmationFrame confirmationFrame;
    private Socket socket;
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;
    public MainFrame() throws IOException, ClassNotFoundException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("ADMIN");
        setSize(500,500);
        toolKit = new ToolKit();
        toolKit.setVisible(true);
        add(toolKit);
        planeCreate = new PlaneCreate();
        planeCreate.setVisible(false);
        add(planeCreate);
        cityCreate = new CityCreate();
        cityCreate.setVisible(false);
        add(cityCreate);
        flightsCreate= new FlightsCreate();
        flightsCreate.setVisible(false);
        add(flightsCreate);

        confirmationFrame = new ConfirmationFrame();
        confirmationFrame.setVisible(false);



        socket = new Socket("127.0.0.1",9090);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());

    }

    public void showToolKit(){
        App.frame.setSize(500,500);
        toolKit.setVisible(true);
        planeCreate.setVisible(false);
        cityCreate.setVisible(false);
        flightsCreate.setVisible(false);
        confirmationFrame.setVisible(false);
    }
    public void showCityCreate(){
        App.frame.setSize(1000,500);
        cityCreate.setVisible(true);
        toolKit.setVisible(false);
        planeCreate.setVisible(false);
        flightsCreate.setVisible(false);
        confirmationFrame.setVisible(false);

    }
    public void showPlaneCreate(){
        App.frame.setSize(1000,500);
        planeCreate.setVisible(true);
        toolKit.setVisible(false);
        cityCreate.setVisible(false);
        flightsCreate.setVisible(false);
        confirmationFrame.setVisible(false);

    }
    public void showFlightCreate(){
        App.frame.setSize(1000,800);
        flightsCreate.setVisible(true);
        planeCreate.setVisible(false);
        toolKit.setVisible(false);
        cityCreate.setVisible(false);
        confirmationFrame.setVisible(false);
    }

    public void showConfirmationFrame(){
        confirmationFrame.setVisible(true);
        flightsCreate.setVisible(true);
        planeCreate.setVisible(false);
        toolKit.setVisible(false);
        cityCreate.setVisible(false);
    }
}

