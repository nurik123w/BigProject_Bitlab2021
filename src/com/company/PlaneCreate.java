
package com.company;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class PlaneCreate extends JPanel {
    private JTextField name;
    private JTextField model;
    private JTextField bclass;
    private JTextField eclass;
    private JComboBox  plane;
    private JLabel choose;
    private ArrayList<Aircraft>aircraft;
    public PlaneCreate(){
        setSize(800,800);
        setLayout(null);
        plane = new JComboBox();
        plane.setSize(800,20);
        plane.setLocation(100,30);
        add(plane);
        JLabel name1 = new JLabel("name");
        name1.setSize(100,30);
        name1.setLocation(100,80);
        add(name1);
        name = new JTextField();
        name.setSize(200,30);
        name.setLocation(200,80);
        add(name);
        JLabel model1 = new JLabel("model");
        model1.setSize(100,30);
        model1.setLocation(100,120);
        add(model1);
        model = new JTextField();
        model.setSize(200,30);
        model.setLocation(200,120);
        add(model);
        JLabel bclass1 = new JLabel("Business class capacity");
        bclass1.setSize(200,30);
        bclass1.setLocation(30,160);
        add(bclass1);
        bclass = new JTextField();
        bclass.setSize(200,30);
        bclass.setLocation(200,160);
        add(bclass);
        JLabel eclass1 = new JLabel("Econom class capacity");
        eclass1.setSize(200,30);
        eclass1.setLocation(30,200);
        add(eclass1);
        eclass = new JTextField();
        eclass.setSize(200,30);
        eclass.setLocation(200,200);
        add(eclass);
        choose = new JLabel();
        choose.setBounds(350,300,300,50);
        add(choose);
        JButton add = new JButton("ADD");
        add.setSize(50,50);
        add.setLocation(100,350);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choose.setText("");
                if (!name.getText().equals("")&&!model.getText().equals("")&&!bclass.getText().equals("")&&!eclass.getText().equals("")){
                        try{
                        String name1 = name.getText();
                        String model1 = model.getText();
                        int bclass1 = Integer.parseInt(bclass.getText());
                        int eclass1 = Integer.parseInt(eclass.getText());
                        Aircraft aircraft = new Aircraft(null, name1, model1, bclass1, eclass1);
                                    App.frame.outputStream.writeObject("ADD_PLANE");
                                    App.frame.outputStream.writeObject(aircraft);
                                    generateBox1();
                                    plane.revalidate();
                        }catch (Exception ew){
                            ew.printStackTrace();
                        }
                    }else choose.setText("no info on text field ");
                name.setText("");
                model.setText("");
                bclass.setText("");
                eclass.setText("");
            }
        });
        add(add);
        JButton update = new JButton("UPDATE");
        update.setBounds(200,350,80,50);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choose.setText("");
                    if(plane.getSelectedIndex()!=0&&!name.getText().equals("")&&!model.getText().equals("")
                            &&!bclass.getText().equals("")&&!eclass.getText().equals("")) {
                        try {
                            int id = plane.getSelectedIndex()-1;
                            String name1 = name.getText();
                            String model1 = model.getText();
                            int bclass1 = Integer.parseInt(bclass.getText());
                            int eclass1 = Integer.parseInt(eclass.getText());
                            Aircraft aircraftt = new Aircraft(aircraft.get(id).getId(),name1,model1,bclass1,eclass1);
                            App.frame.outputStream.writeObject("EDIT_AIRCRAFT");
                            App.frame.outputStream.writeObject(aircraftt);
                            generateBox1();
                            plane.revalidate();
                            name.setText("");
                            model.setText("");
                            bclass.setText("");
                            eclass.setText("");
                            plane.setSelectedIndex(0);
                        } catch (Exception a) {
                            a.printStackTrace();
                        }
                    }else choose.setText("no info on text field or box");
            }
        });
        add(update);
        JButton delete = new JButton("DELETE");
        delete.setBounds(300,350,80,50);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(plane.getSelectedIndex()!=0){
                    try {
                        App.frame.outputStream.writeObject("DELETE_AIRCRAFT");
                        App.frame.outputStream.writeObject(aircraft.get(plane.getSelectedIndex()-1).getId());
                        plane.setSelectedIndex(0);
                        generateBox1();
                        plane.revalidate();
                    } catch (Exception qa) {
                        qa.printStackTrace();
                    }
                } else choose.setText("haven't chosen info at box ");
            }
        });
        add(delete);
        JButton back = new JButton("BACK");
        back.setSize(50,50);
        back.setLocation(400,350);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                model.setText("");
                bclass.setText("");
                eclass.setText("");
                plane.setSelectedIndex(0);
                choose.setText("");
                App.frame.showToolKit();
            }
        });
        add(back);
    }
    public void generateBox1() throws IOException, ClassNotFoundException {
        plane.removeAllItems();
        App.frame.outputStream.writeObject("GET_ALL_AIRCRAFTS");
        aircraft =(ArrayList<Aircraft>)App.frame.inputStream.readObject();
        String n= "choose aircraft to update or delete";
        plane.addItem(n);
        for(int i=0;i<aircraft.size();i++){
            plane.addItem(new Aircraft(aircraft.get(i).getId(),aircraft.get(i).getName(),aircraft.get(i).getModel(),
                    aircraft.get(i).getBusiness_class_capacity(),aircraft.get(i).getEconom_class_capacity()));
        }
    }
}

