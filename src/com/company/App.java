
package com.company;
import java.io.IOException;
public class App {
    public static MainFrame frame;
    public static ConfirmationFrame confirmationFrame;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        frame = new MainFrame();
        frame.setVisible(true);

    }
}
