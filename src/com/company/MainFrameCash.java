package com.company;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainFrameCash extends JFrame {
    Entrance entrance;
    MainMenuCash mainMenu;
    ConfirmDelete delete;
    ChooseFlighBydate dateFlight;
    NullShow nullShow;
    private Socket socket;
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;
    public MainFrameCash() throws IOException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("CASHIER");
        setSize(500,500);

        entrance = new Entrance();
        entrance.setVisible(true);
        add(entrance);
        mainMenu = new MainMenuCash();
        mainMenu.setVisible(false);
        add(mainMenu);
        delete = new ConfirmDelete();
        delete.setVisible(false);
        dateFlight = new ChooseFlighBydate();
        dateFlight.setVisible(false);

        nullShow = new NullShow();
        nullShow.setVisible(false);

        socket = new Socket("127.0.0.1",9090);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());

    }
    public void  showEntrance(){
        setSize(500,500);
        entrance.setVisible(true);
        mainMenu.setVisible(false);
        delete.setVisible(false);
        dateFlight.setVisible(false);
        nullShow.setVisible(false);
    }
    public void showMenu(){
        setSize(1000,800);
        mainMenu.setVisible(true);
        entrance.setVisible(false);
        delete.setVisible(false);
        dateFlight.setVisible(false);
        nullShow.setVisible(false);
    }
    public void confirmDelete(){
        delete.setVisible(true);
        mainMenu.setVisible(true);
        entrance.setVisible(false);
        dateFlight.setVisible(false);
        nullShow.setVisible(false);
    }
    public void showDateFLight(){
        dateFlight.setVisible(true);
        mainMenu.setVisible(true);
        delete.setVisible(false);
        entrance.setVisible(false);
        nullShow.setVisible(false);
    }
    public void showNullShow(){
        mainMenu.setVisible(true);
        nullShow.setVisible(true);
        dateFlight.setVisible(false);
        delete.setVisible(false);
        entrance.setVisible(false);
    }
}
