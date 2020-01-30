package components;

import javax.swing.*;
import java.awt.*;

/*
 * BottomPanel
 *  -Object containing two inputs and a button
 */

public class BottomPanel {

    private JPanel panel;
    private JLabel regex_label;
    private JTextField regex_field;
    private JTextField format_field;
    private JButton button;

    //Constructor
    public BottomPanel(String label_text1, String label_text2, String button_text) {
        //Create panel
        panel = new JPanel(new GridLayout(2, 2));

        //Create labels
        String tab_space = "\t\t\t\t\t\t\t";
        JLabel regex_label = new JLabel(tab_space+label_text1);
        JLabel space = new JLabel(" ");
        JLabel format_label = new JLabel(tab_space+label_text2);

        //Create components; two text fields and a button
        regex_field = new JTextField(10);
        format_field = new JTextField(10);
        button = new JButton(button_text);

        //Add all pieces to panel
        panel.add(regex_label);
        panel.add(regex_field);
        panel.add(space);
        panel.add(format_label);
        panel.add(format_field);
        panel.add(button);
    }

    //Getters and Setters
    public JButton getButton() {
        return this.button;
    }

    public JPanel getPanel() {
        return this.panel;
    }

    public String getRegexString() {
        return this.regex_field.getText();
    }

    public String getFormatString() {
        return this.format_field.getText();
    }

}
