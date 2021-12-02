package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Entrance extends JPanel {
    public Entrance(){
        setSize(500,500);
        setLayout(null);
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(100,100,100,20);
        add(loginLabel);
        JTextField loginText = new JTextField();
        loginText.setBounds(200,100,100,20);
        add(loginText);
        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(100,150,100,10);
        add(passLabel);
        JTextField passText = new JTextField();
        passText.setBounds(200,150,100,20);
        add(passText);
        JLabel label = new JLabel();
        label.setBounds(150,250,500,20);
        add(label);

        JButton confirm = new JButton("CONFIRM");
        confirm.setBounds(200,200,80,20);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(loginText.getText().equals("login")&&passText.getText().equals("pass")){
                    loginText.setText("");
                    passText.setText("");
                    label.setText("");
                    try {
                        CashierApp.frame.mainMenu.generateTickets();
                        CashierApp.frame.mainMenu.generateAllFlights();
                        CashierApp.frame.showMenu();
                    }catch (Exception s){
                        s.printStackTrace();
                    }
                } else label.setText("INVALID PASSWORD OR LOGIN,TRY AGAIN");
                loginText.setText("");
                passText.setText("");
            }
        });
        add(confirm);

    }
}
