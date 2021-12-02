package com.company;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FlightsCreate extends JPanel {
    JComboBox aircraft;
    JComboBox depCity;
    JComboBox arrivCity;
    JButton add;
    JLabel choose;
    JTextField timeText;
    JTextField economText;
    JTextField businessText;
    JTable flights;
    JScrollPane scrollPane;
    ArrayList<Aircraft> a;
    ArrayList<Cities> c;
    ArrayList<Flights>f;
    JDateChooser dateChooser;

    long selectedColumn;
    String[] head  ={"DATE","AIRCRAFT","DEPARTURE CITY","ARRIVAL CITY","DEPARTURE TIME","STANDART SEAT PRICE","BUSINESS SEAT PRICE"} ;
    public FlightsCreate() throws IOException, ClassNotFoundException {
        setSize(1000,800);
        setLayout(null);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        flights = new JTable();
        flights.setFont(new Font("Calibri", Font.PLAIN, 12));
        flights.setRowHeight(30);
        scrollPane = new JScrollPane(flights);
        scrollPane.setBounds(10,20,980,300);
        add(scrollPane);

        aircraft = new JComboBox();
        aircraft.setSize(500,20);
        aircraft.setLocation(350,400);
        add(aircraft);

        depCity = new JComboBox();
        depCity.setSize(500,20);
        depCity.setLocation(350,435);
        add(depCity);

        arrivCity = new JComboBox();
        arrivCity.setSize(500,20);
        arrivCity.setLocation(350,470);
        add(arrivCity);

        JLabel timeLabel = new JLabel("Departure time");
        timeLabel.setBounds(100,400,100,20);
        add(timeLabel);
        timeText = new JTextField();
        timeText.setBounds(230,400,100,20);
        add(timeText);
        JLabel economLabel = new JLabel("Econom place price");
        economLabel.setBounds(100,435,200,20);
        add(economLabel);
        economText = new JTextField();
        economText.setBounds(230,435,100,20);
        add(economText);

        JLabel businessLabel = new JLabel("Business place price");
        businessLabel.setBounds(100,470,200,20);
        add(businessLabel);
        businessText = new JTextField();
        businessText.setBounds(230,470,100,20);
        add(businessText);

        choose = new JLabel();
        choose.setBounds(400,505,300,50);
        add(choose);

        JLabel datelabel = new JLabel("Chose the date");
        datelabel.setBounds(500,500,200,30);
        add(datelabel);
        dateChooser = new JDateChooser();
        dateChooser.setBounds(630,500,200,30);
        add(dateChooser);

        add = new JButton("ADD");
        add.setSize(200,30);
        add.setLocation(400,620);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (aircraft.getSelectedIndex() != 0 && depCity.getSelectedIndex()!=0&& arrivCity.getSelectedIndex()!=0&&
                        !timeText.getText().equals("") && !economText.getText().equals("") && !businessText.getText().equals("") ){
                    try {
                        int s =  Math.toIntExact(a.get(aircraft.getSelectedIndex()-1).getId());
                        int w =  Math.toIntExact(c.get(depCity.getSelectedIndex()-1).getId());
                        int d =  Math.toIntExact((c.get(arrivCity.getSelectedIndex()-1).getId()));
                        String date = sdf.format(dateChooser.getDate());
                        Flights f = new Flights(null,s,w,d,
                                timeText.getText(),
                                Integer.parseInt(economText.getText()),
                                Integer.parseInt(businessText.getText()),date);
                            App.frame.outputStream.writeObject("ADD_FLIGHTS");
                            App.frame.outputStream.writeObject(f);
                            generateBoxAll();
                    } catch (Exception ss) {
                        ss.printStackTrace();
                    }
                    aircraft.setSelectedIndex(0);
                    depCity.setSelectedIndex(0);
                    arrivCity.setSelectedIndex(0);
                    timeText.setText("");
                    economText.setText("");
                    businessText.setText("");
                } else
                    choose.setText("You haven't chosen preferred plane or cities....");
            }
        });
        add(add);
        JButton update = new JButton("UPDATE");
        update.setBounds(400,650,95,35);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flights.getSelectedColumn()!=-1 && aircraft.getSelectedIndex() !=0 && depCity.getSelectedIndex()!=0 && arrivCity.getSelectedIndex()!=0 &&
                        !timeText.getText().equals("") && !economText.getText().equals("") && !businessText.getText().equals("")){
                    try {
                        long o = f.get(flights.getSelectedRow()).getId();
                        int s =  Math.toIntExact(a.get(aircraft.getSelectedIndex()+1).getId());
                        int w =  Math.toIntExact(c.get(depCity.getSelectedIndex()+1).getId());
                        int d =  Math.toIntExact((c.get(arrivCity.getSelectedIndex()+1).getId()));
                        String date = sdf.format(dateChooser.getDate());
                        Flights f = new Flights(o,s,w,d,
                                timeText.getText(),
                                Integer.parseInt(economText.getText()),
                                Integer.parseInt(businessText.getText()),date);
                        App.frame.outputStream.writeObject("EDIT_FLIGHTS");
                        generateBoxAll();
                    } catch (Exception ss) {
                        ss.printStackTrace();
                    }

                    aircraft.setSelectedIndex(0);
                    depCity.setSelectedIndex(0);
                    arrivCity.setSelectedIndex(0);
                    timeText.setText("");
                    economText.setText("");
                    businessText.setText("");
                    choose.setText("");
                } else
                    choose.setText("You haven't chosen preferred plane or cities....");
            }
        });
        add(update);
        JButton delete = new JButton("DELETE");
        delete.setBounds(500,650,95,35);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(flights.getSelectedColumn()!=-1){
                    try {
                        selectedColumn =f.get(flights.getSelectedRow()).getId();
                        App.frame.confirmationFrame.generateExactFlight();
                        App.frame.showConfirmationFrame();
                    } catch (Exception q) {
                        q.printStackTrace();
                    }
                    choose.setText("");
                } else choose.setText("haven't chosen flight to delete....");

            }
        });
        add(delete);

        JButton back = new JButton("BACK");
        back.setBounds(400,700,200,30);
        add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aircraft.setSelectedIndex(0);
                depCity.setSelectedIndex(0);
                arrivCity.setSelectedIndex(0);
                timeText.setText("");
                economText.setText("");
                businessText.setText("");
                choose.setText("");
                App.frame.showToolKit();
            }
        });
    }

    public void generateBoxAll() throws IOException, ClassNotFoundException {
        flights.removeAll();
        App.frame.outputStream.writeObject("GET_ALL_FLIGHTS");
        f = (ArrayList<Flights>) App.frame.inputStream.readObject();
        Object[][] data = new Object[f.size()][7];
        for(int i=0;i<f.size();i++){
            try{
                data[i][0] = f.get(i).getDate();
                data[i][1] = f.get(i).getAircraft().getForTable();
                data[i][2] = f.get(i).getDeparture().getForTable();
                data[i][3] = f.get(i).getArrivalCity().getForTable();
                data[i][4] = f.get(i).getDeparture_time();
                data[i][5] = f.get(i).getEconom_place_price();
                data[i][6] = f.get(i).getBusiness_place_price();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        DefaultTableModel model = new DefaultTableModel(data,head);
        flights.setModel(model);
        flights.revalidate();
        scrollPane.revalidate();


        aircraft.removeAllItems();
        App.frame.outputStream.writeObject("GET_ALL_AIRCRAFTS");
        a = (ArrayList<Aircraft>) App.frame.inputStream.readObject();
        String sn ="Choose an Airplane to add";
        aircraft.addItem(sn);
        for(int i=0;i<a.size();i++){
            aircraft.addItem( new Aircraft(a.get(i).getId(),a.get(i).getName(),a.get(i).getModel(),a.get(i).getBusiness_class_capacity(),a.get(i).getEconom_class_capacity()));
        }

        depCity.removeAllItems();
        arrivCity.removeAllItems();
        App.frame.outputStream.writeObject("GET_ALL_CITIES");
        c = (ArrayList<Cities>) App.frame.inputStream.readObject();
        String nw = "Choose departure city to add ";
        String nn="Choose arrival city to add";
        depCity.addItem(nw);
        arrivCity.addItem(nn);
        for(int i=0;i<c.size();i++){
           depCity.addItem(new Cities(c.get(i).getId(),c.get(i).getName(),c.get(i).getCountry(),c.get(i).getShort_name()));
           arrivCity.addItem(new Cities(c.get(i).getId(),c.get(i).getName(),c.get(i).getCountry(),c.get(i).getShort_name()));
       }
    }
}
