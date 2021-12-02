package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NullShow extends JFrame {
    JList<String> available;
    public NullShow(){
        setTitle("CHOOSE THE PREFERED FLIGHT");
        setSize(500, 200);
        setLayout(null);
        JLabel label = new JLabel("THERE IS NO FLIGHT ON THAT DAY");
        label.setBounds(130,50,400,20);
        add(label);
        JLabel label1 =new JLabel("WE ARE WORKING ON EXPANDING IT");
        label1.setBounds(130,70,400,20);
        add(label1);
        JButton button = new JButton("OKAY");
        button.setBounds(180,150,100,20);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CashierApp.frame.showMenu();
            }
        });
        add(button);
    }
}
