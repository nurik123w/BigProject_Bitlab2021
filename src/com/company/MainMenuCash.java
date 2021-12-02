package com.company;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainMenuCash extends JPanel {
    ArrayList<Tickets> t;
    ArrayList<Flights> f;
    JTable tickets ;
    JCheckBox checkBox, checkBox1;
    JTextField nameText, surnameText, passportText;
    JButton add, update, delete,selectDate;
    JLabel choice, classType;
    int count = 1;
    int count1 = 1;
    JScrollPane pane1;
    Long id;
    String date;

    String [] headTicket = {"DATE","FLIGHT","NAME","SURNAME","PASSPORT NUM","TICKET TYPE"};
    public MainMenuCash() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        setSize(1000, 800);
        setLayout(null);
        JLabel labell = new JLabel("CHOOSE TICKET TO UPDATE");
        labell.setBounds(410,20,250,20);
        add(labell);
        tickets = new JTable();
        pane1 = new JScrollPane(tickets);
        pane1.setBounds(50, 50, 900, 130);
        add(pane1);
        JLabel labeln = new JLabel("CHOOSE THE DATE");
        labeln.setBounds(570,260,200,20);
        add(labeln);

        JDateChooser datechooser = new JDateChooser();
        datechooser.setBounds(400,290,400,30);
        add(datechooser);

        selectDate = new JButton("SELECT");
        selectDate.setBounds(500,320,100,35);
        selectDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    try {
                        date = sdf.format(datechooser.getDate());
                        CashierApp.frame.dateFlight.generateFlights(date);
                    } catch (Exception es) {
                        es.printStackTrace();
                    }
            }
        });
        add(selectDate);


        JLabel namelabel = new JLabel("NAME");
        namelabel.setBounds(50, 290, 100, 20);
        add(namelabel);
        nameText = new JTextField();
        nameText.setBounds(150, 290, 100, 20);
        add(nameText);
        JLabel surnameLabel = new JLabel("SURNAME");
        surnameLabel.setBounds(50, 320, 100, 20);
        add(surnameLabel);
        surnameText = new JTextField();
        surnameText.setBounds(150, 320, 100, 20);
        add(surnameText);
        passportText = new JTextField();
        passportText.setBounds(150, 350, 100, 20);
        add(passportText);
        JLabel passLabel = new JLabel("PASSPORT NUM");
        passLabel.setBounds(50, 350, 100, 20);
        add(passLabel);
        JLabel ec = new JLabel("Standard class");
        ec.setBounds(50, 400, 100, 20);
        add(ec);

        checkBox = new JCheckBox();
        checkBox.setBounds(70, 420, 20, 20);
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count % 2 != 0) {
                    checkBox1.setEnabled(false);
                    classType.setText("Standard class");
                } else {
                    checkBox1.setEnabled(true);
                }
                count++;
            }
        });
        add(checkBox);

        JLabel bc = new JLabel("Business class");
        bc.setBounds(150, 400, 100, 20);
        add(bc);
        checkBox1 = new JCheckBox();
        checkBox1.setBounds(190, 420, 20, 20);
        checkBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count1 % 2 != 0) {
                    checkBox.setEnabled(false);
                    classType.setText("Business class");
                } else {
                    checkBox.setEnabled(true);
                }
                count1++;
            }
        });
        add(checkBox1);


        choice = new JLabel();
        choice.setBounds(30, 500, 800, 20);
        add(choice);

        classType = new JLabel();
        classType.setBounds(900, 900, 200, 20);
        add(classType);

        add = new JButton("ADD");
        add.setBounds(350, 650, 80, 30);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!nameText.getText().equals("") && !surnameText.getText().equals("")
                        && !passportText.getText().equals("") && checkBox.isSelected() || checkBox1.isSelected()) {

                       long s = CashierApp.frame.dateFlight.f.get(CashierApp.frame.dateFlight.flightss).getId();
                    try {
                        CashierApp.frame.outputStream.writeObject("ADD_TICKETS");
                        CashierApp.frame.outputStream.writeObject(new Tickets(null, (int) s, nameText.getText(),
                                surnameText.getText(), passportText.getText(), classType.getText()));
                        generateTickets();
                        if (checkBox.isSelected()) {
                            checkBox.setSelected(false);
                            checkBox1.setEnabled(true);
                        } else if (checkBox1.isSelected()) {
                            checkBox1.setSelected(false);
                            checkBox.setEnabled(true);
                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                        choice.setText("");
                        nameText.setText("");
                        surnameText.setText("");
                        passportText.setText("");

                } else choice.setText("haven't chosen flights or other info");
            }
        });
        add(add);

        update = new JButton("UPDATE");
        update.setBounds(450, 650, 80, 30);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tickets.getSelectedRow() > -1 && !nameText.getText().equals("") && !surnameText.getText().equals("")
                        && !passportText.getText().equals("") && checkBox.isSelected() || checkBox1.isSelected()) {
                    long w = t.get(tickets.getSelectedRow()).getId();
                    long s = CashierApp.frame.dateFlight.f.get(CashierApp.frame.dateFlight.flightss).getId();
                    try {
                        CashierApp.frame.outputStream.writeObject("EDIT_TICKETS");
                        CashierApp.frame.outputStream.writeObject(new Tickets(w, (int) s, nameText.getText(), surnameText.getText(),
                                passportText.getText(), classType.getText()));
                        generateTickets();
                        if (checkBox.isSelected()) {
                            checkBox.setSelected(false);
                            checkBox1.setEnabled(true);
                        } else if (checkBox1.isSelected()) {
                            checkBox1.setSelected(false);
                            checkBox.setEnabled(true);
                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                        choice.setText("");
                        nameText.setText("");
                        surnameText.setText("");
                        passportText.setText("");

                } else choice.setText("haven't chosen tickets/flights or other");
            }
        });
        add(update);

        delete = new JButton("DELETE");
        delete.setBounds(550, 650, 80, 30);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tickets.getSelectedRow() != -1) {
                    try {
                        id=t.get(tickets.getSelectedRow()).getId();
                        CashierApp.frame.delete.generateExactFlight();
                        CashierApp.frame.confirmDelete();
                        choice.setText("");
                    } catch (Exception o) {
                        o.printStackTrace();
                    }
                } else choice.setText("haven't chosen ticket to delete");
            }
        });
        add(delete);
        JButton refresh = new JButton("GET UPDATE FROM DATABASE");
        refresh.setBounds(350,680,300,30);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CashierApp.frame.dateFlight.generateFlights(date);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });
        add(refresh);
        JButton exit = new JButton("EXIT");
        exit.setBounds(350, 720, 300, 30);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox.isSelected()) {
                    checkBox.setSelected(false);
                    checkBox1.setEnabled(true);
                } else if (checkBox1.isSelected()) {
                    checkBox1.setSelected(false);
                    checkBox.setEnabled(true);
                }
                choice.setText("");
                nameText.setText("");
                surnameText.setText("");
                passportText.setText("");
                CashierApp.frame.showEntrance();
            }
        });
        add(exit);
    }


    public void generateTickets() throws IOException, ClassNotFoundException {
        tickets.removeAll();
        CashierApp.frame.outputStream.writeObject("GET_ALL_TICKETS");
        t = (ArrayList<Tickets>) CashierApp.frame.inputStream.readObject();
        Object[][] data = new Object[t.size()][6];
        for (int i = 0; i < t.size(); i++){
            try{
                data[i][0] = t.get(i).getFlights().getDate();
                data[i][1] = t.get(i).getFlights().getForTable();
                data[i][2] = t.get(i).getName();
                data[i][3] = t.get(i).getSurname();
                data[i][4] = t.get(i).getPassport_number();
                data[i][5] = t.get(i).getTicket_type();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
                DefaultTableModel model = new DefaultTableModel(data,headTicket);
                tickets.setModel(model);
                tickets.revalidate();
                pane1.revalidate();
    }

        public void generateAllFlights() throws IOException, ClassNotFoundException {
            CashierApp.frame.outputStream.writeObject("GET_ALL_FLIGHTS");
            f=(ArrayList<Flights>)CashierApp.frame.inputStream.readObject();
        }

}
