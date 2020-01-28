import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

import java.util.List;
import java.util.ArrayList;

public class RegexGUI implements ActionListener {

    JFrame frame;
    BottomPanel bottom;
    TextBox text_box;
    JTextArea text_area;

    public RegexGUI() {
        frame = new JFrame("Regex GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);

        text_box = new TextBox();
        frame.getContentPane().add(BorderLayout.CENTER, text_box.getScroll());

        bottom = new BottomPanel("Regex:", "Format:", "Go");
        bottom.getButton().addActionListener(this);
        frame.getContentPane().add(BorderLayout.PAGE_END, bottom.getPanel());

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String re_pattern = bottom.getRegexString();
        String re_string = text_box.getText();

        Pattern p = Pattern.compile(re_pattern);
        Matcher m = p.matcher(re_string);

        List<String[]> matches = new ArrayList<>();
        while (m.find()) {
            int n = m.groupCount() + 1;
            String[] groups = new String[n];
            for(int i = 0; i < n; i++) {
                groups[i] = m.group(i);
            }
            matches.add(groups);

        }

        LanguageFormat l_format = new LanguageFormat("([^=\\\\]*)\\\\(\\d)");

        String format_string = bottom.getFormatString();
        l_format.setMatcher(format_string);
        l_format.translateLanguage();
        String new_text = l_format.printWithFormat(matches);

        text_box.setText(new_text);

    }

    public static void main(String args[]){
       RegexGUI gui = new RegexGUI();
    }
}
