package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ConfirmDelete extends JFrame {
    JLabel label;
    Tickets tickets;

    public ConfirmDelete(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("ARE YOU SURE TO DELETE???");
        setSize(700, 200);
        setLayout(null);


        label = new JLabel("s");
        label.setBounds(80,80,1000,40);
        add(label);

        JButton confirm = new JButton("CONFIRM");
        confirm.setBounds(400,150,100,20);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CashierApp.frame.outputStream.writeObject("DELETE_TICKETS");
                    CashierApp.frame.outputStream.writeObject(CashierApp.frame.mainMenu.id);
                    CashierApp.frame.mainMenu.generateTickets();
                    CashierApp.frame.showMenu();
                    label.setText("");
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
                label.setText("");
                CashierApp.frame.showMenu();
            }
        });
        add(back);
    }

    public void generateExactFlight() throws IOException, ClassNotFoundException {
        tickets = CashierApp.frame.mainMenu.t.get(CashierApp.frame.mainMenu.tickets.getSelectedRow());
        System.out.println(tickets);
        label.setText(String.valueOf(tickets.getFlights().getForTable()+"  Passenger: "+tickets.getName()+" "+tickets.getSurname()+"  ID: "+tickets.getPassport_number()+" "+tickets.getTicket_type()));
    }
}