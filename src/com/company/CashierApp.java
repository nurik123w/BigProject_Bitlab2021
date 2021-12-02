package com.company;

import java.io.IOException;

public class CashierApp {
    public static MainFrameCash frame ;
    public static void main(String[] args) throws IOException{
        frame = new MainFrameCash();
        frame.setVisible(true);
    }
}
