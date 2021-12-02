package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ChooseFlighBydate extends JFrame {
    JTable flights;
    JScrollPane pane;
    ArrayList<Flights> f;
    String[] head = {"DATE", "AIRCRAFT", "DEPARTURE CITY", "ARRIVAL CITY", "DEPARTURE TIME", "STANDART COST", "BUSINESS COST"};
    int flightss;
    JButton button;
    public ChooseFlighBydate(){

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("CHOOSE THE PREFERED FLIGHT");
        setSize(1000, 500);
        setLayout(null);
        JLabel label = new JLabel();
        label.setBounds(210,300,200,20);
        add(label);
        flights = new JTable();
        pane = new JScrollPane(flights);
        pane.setBounds(50, 50, 900, 100);
        add(pane);
        button = new JButton("SELECT");
        button.setBounds(200,250,100,20);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(flights.getSelectedRow()!=-1) {
                    flightss = flights.getSelectedRow();
                    label.setText("");
                    CashierApp.frame.showMenu();
                }else label.setText("Chose the flight");
            }
        });
        add(button);
        JButton back = new JButton("CANCEL");
        back.setBounds(300,250,100,20);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("");
                CashierApp.frame.showMenu();
            }
        });
        add(back);


    }
    public void generateFlights(String date) throws IOException, ClassNotFoundException {
        flights.removeAll();
        CashierApp.frame.outputStream.writeObject("GET");
        CashierApp.frame.outputStream.writeObject(date);
        f = (ArrayList<Flights>) CashierApp.frame.inputStream.readObject();
        Object[][] data = new Object[f.size()][7];
        for (int i = 0; i < f.size(); i++) {
            try {
                data[i][0] = f.get(i).getDate();
                data[i][1] = f.get(i).getAircraft().getForTable();
                data[i][2] = f.get(i).getDeparture().getForTable();
                data[i][3] = f.get(i).getArrivalCity().getForTable();
                data[i][4] = f.get(i).getDeparture_time();
                data[i][5] = f.get(i).getEconom_place_price();
                data[i][6] = f.get(i).getBusiness_place_price();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        DefaultTableModel model = new DefaultTableModel(data, head);
        flights.setModel(model);
        flights.revalidate();
        pane.revalidate();

        if(f.isEmpty()) {
            CashierApp.frame.showNullShow();
        } else {
            CashierApp.frame.showDateFLight();
        }
    }
}
