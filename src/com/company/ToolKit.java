
package com.company;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ToolKit extends JPanel{
    protected JButton plane;
    protected JButton city;
    protected JButton flights;

    public ToolKit(){
        setSize(500,500);
        setLayout(null);
        city = new JButton("~CITY~");
        city.setSize(200,30);
        city.setLocation(150,100);
        add(city);
        city.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    App.frame.cityCreate.generateCityBox();
                    App.frame.showCityCreate();
                } catch (Exception ew) {
                    ew.printStackTrace();
                }
            }
        });
        plane = new JButton("~AIRCRAFT~");
        plane.setSize(200,30);
        plane.setLocation(150,150);
        add(plane);
        plane.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    App.frame.planeCreate.generateBox1();
                    App.frame.showPlaneCreate();
                } catch (Exception s) {
                    s.printStackTrace();
                }
            }
        });
        flights = new JButton("~FLIGHTS~");
        flights.setSize(200,30);
        flights.setLocation(150,200);
        add(flights);
        flights.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        try {
                        App.frame.flightsCreate.generateBoxAll();
                        App.frame.showFlightCreate();
                        }catch (Exception a){
                             a.printStackTrace();
                        }
            }
        });
    }
}

