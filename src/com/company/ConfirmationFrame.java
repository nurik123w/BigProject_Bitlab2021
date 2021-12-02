package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ConfirmationFrame extends JFrame {
    JTable flights;
    JScrollPane scrollPane;
    Flights f;
    JLabel label,label1;

    public ConfirmationFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("ARE YOU SURE TO DELETE???");
        setSize(1000, 200);
        setLayout(null);

        label = new JLabel();
        label.setBounds(50,70,1000,40);
        add(label);
        label1 = new JLabel();
        label1.setBounds(50,90,1000,40);
        add(label1);

        JButton confirm = new JButton("CONFIRM");
        confirm.setBounds(400,150,100,20);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    App.frame.outputStream.writeObject("DELETE_FLIGHTS");
                    App.frame.outputStream.writeObject(App.frame.flightsCreate.selectedColumn);
                    App.frame.flightsCreate.generateBoxAll();
                    App.frame.showFlightCreate();
                    label.setText("");
                    label1.setText("");
                    } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        add(confirm);

    JButton back = new JButton("CANCEL");
    back.setBounds(500,150,100,20);
    back.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            App.frame.showFlightCreate();
        }
    });
    add(back);
    }

    public void generateExactFlight() throws IOException, ClassNotFoundException {
        f=App.frame.flightsCreate.f.get(App.frame.flightsCreate.flights.getSelectedRow());
        label.setText(String.valueOf("Airplane: "+f.getAircraft()+" Departure city"+f.getDeparture()));
        label1.setText(String.valueOf("Arrival city: "+f.getArrivalCity()+", Departure time: "+f.getDeparture_time()+"\n, Business class price:"+f.getBusiness_place_price()+", Standard price: "+f.getEconom_place_price()));
    }
}
