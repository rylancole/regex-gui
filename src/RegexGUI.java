import components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

import java.util.List;
import java.util.ArrayList;

/*
 * RegexGUI
 *  -GUI for reformatting blocks of text
 *  -Takes input text and two commands; regex and format
 *  -Regex command: finds matching groups in input text
 *  -Format command: rewrites groups in given format, defined
 *  by FluffCalls
 */

public class RegexGUI implements ActionListener {

    JFrame frame;
    BottomPanel bottom;
    TextBox text_box;
    JTextArea text_area;

    //Constructor
    public RegexGUI() {
        //Create frame of GUI
        frame = new JFrame("Regex GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);

        //Add textbox for input text
        text_box = new TextBox();
        frame.getContentPane().add(BorderLayout.CENTER, text_box.getScroll());

        //Add bottom panel for commands
        bottom = new BottomPanel("Regex:", "Format:", "Go");
        bottom.getButton().addActionListener(this);
        frame.getContentPane().add(BorderLayout.PAGE_END, bottom.getPanel());

        frame.setVisible(true);
    }

    //Action Listener for 'Go' button
    public void actionPerformed(ActionEvent e) {
        //Get regex command and input text to peform it on
        String re_pattern = bottom.getRegexString();
        String re_string = text_box.getText();

        //Get matches to regex
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

        //Define a language from the format command
        LanguageFormat l_format = new LanguageFormat("([^=\\\\]*)\\\\(\\d)");
        String format_string = bottom.getFormatString();
        l_format.setMatcher(format_string);
        l_format.translateLanguage();

        //Apply language rules to regex matches
        String new_text = l_format.printWithFormat(matches);

        //Return results, replacing input
        text_box.setText(new_text);

    }

    public static void main(String args[]){
       RegexGUI gui = new RegexGUI();
    }
}
