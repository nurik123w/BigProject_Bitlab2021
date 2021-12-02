
package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class CityCreate extends JPanel {
    JComboBox city;
    JLabel choose;
    ArrayList<Cities>cities;
    public CityCreate() {
        setSize(800, 800);
        setLayout(null);
        city = new JComboBox();
        city.setSize(800,20);
        city.setLocation(100,30);
        add(city);
        JLabel name1 = new JLabel("name");
        name1.setSize(100, 30);
        name1.setLocation(100, 80);
        add(name1);
        JTextField name = new JTextField();
        name.setSize(200, 30);
        name.setLocation(200, 80);
        add(name);
        JLabel country1 = new JLabel("country");
        country1.setSize(100, 30);
        country1.setLocation(100, 120);
        add(country1);
        JTextField country = new JTextField();
        country.setSize(200, 30);
        country.setLocation(200, 120);
        add(country);
        JLabel short_name1 = new JLabel("short name");
        short_name1.setSize(100, 30);
        short_name1.setLocation(100, 160);
        add(short_name1);
        JTextField short_name = new JTextField();
        short_name.setSize(200, 30);
        short_name.setLocation(200, 160);
        add(short_name);
        choose = new JLabel();
        choose.setBounds(350,300,300,50);
        add(choose);
        JButton add = new JButton("ADD");
        add.setSize(80, 80);
        add.setLocation(100, 350);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!name.getText().equals("") && !country.getText().equals("") && !short_name.equals("")){
                    try {
                        String name1 = name.getText();
                        String country1 = country.getText();
                        String shortName = short_name.getText();
                        Cities c = new Cities(null, name1, country1, shortName);
                        App.frame.outputStream.writeObject("ADD_CITY");
                        App.frame.outputStream.writeObject(c);
                        generateCityBox();
                        city.revalidate();
                        name.setText("");
                        country.setText("");
                        short_name.setText("");
                        choose.setText("");
                    }catch (Exception o){
                        o.printStackTrace();
                    }
                }else choose.setText("no info on text field");
            }
        });
        add(add);

        JButton edit = new JButton("UPDATE");
        edit.setSize(80,80);
        edit.setLocation(200,350);
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(city.getSelectedIndex()!=0&&!name.getText().equals("") && !country.getText().equals("") && !short_name.equals("")){
                    try {
                        int city_id = city.getSelectedIndex() - 1;
                        String name1 = name.getText();
                        String country1 = country.getText();
                        String shortName = short_name.getText();
                        Cities c = new Cities(cities.get(city_id).getId(), name1, country1, shortName);
                        App.frame.outputStream.writeObject("EDIT_CITY");
                        App.frame.outputStream.writeObject(c);
                        generateCityBox();
                        name.setText("");
                        country.setText("");
                        short_name.setText("");
                        city.setSelectedIndex(0);
                        choose.setText("");
                    }catch (Exception o){
                        o.printStackTrace();
                    }
                }else choose.setText("no info on text field or box");
            }
        });
        add(edit);
        JButton delete = new JButton("DELETE");
        delete.setBounds(300,350,80,80);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(city.getSelectedIndex()!=0){
                    try {
                        int id = city.getSelectedIndex()-1;
                        App.frame.outputStream.writeObject("DELETE_CITY");
                        App.frame.outputStream.writeObject(cities.get(id).getId());
                        generateCityBox();
                        city.revalidate();
                        name.setText("");
                        country.setText("");
                        short_name.setText("");
                        city.setSelectedIndex(0);
                        choose.setText("");
                    }catch (Exception o){
                        o.printStackTrace();
                    }
                }else choose.setText("no info on text field or box");
            }
        });
        add(delete);
        JButton back = new JButton("BACK");
        back.setSize(80,80);
        back.setLocation(400,350);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                country.setText("");
                short_name.setText("");
                choose.setText("");
                App.frame.showToolKit();
            }
        });
        add(back);
    }
    public void generateCityBox() throws IOException, ClassNotFoundException {
        city.removeAllItems();
        city.revalidate();
        App.frame.outputStream.writeObject("GET_ALL_CITIES");
        cities = (ArrayList<Cities>) App.frame.inputStream.readObject();
        String n = "Choose city to update or delete";
        city.addItem(n);
        for(int i=0;i<cities.size();i++){
            city.addItem(new Cities(cities.get(i).getId(),cities.get(i).getName(),cities.get(i).getCountry(),cities.get(i).getShort_name()));
        }
    }
}