package rds.testbed;

import javax.swing.*;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 9/4/11
 * Time: 9:44 PM
 */
public class StreamToTextField {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("I be streamin!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextField field = new JTextField();
        frame.add(field);
        frame.pack();
        frame.setVisible(true);

        System.out.println("Enter stuff to write to the field");
        char c;
        InputStreamReader in = new InputStreamReader(System.in, "UTF-8");
        while ((c = (char) in.read()) != -1) {
            String currentText = field.getText();
            field.setText(currentText + c);
            Thread.sleep(250);
        }
    }
}
