package components;

import javax.swing.*;
import java.awt.*;
import java.util.regex.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * LanguageFormat
 *  -Object utilized to store String format
 *  -Use regex and FluffCalls to print Strings in a
 *  given format
 */

public class LanguageFormat {

    private String re_string;
    private String match_string;
    private Pattern pattern;
    private Matcher matcher;
    private List<FluffCall> fluff_calls = new ArrayList<>();

    //Constructor
    public LanguageFormat(String re_string) {
        this.re_string = re_string;
        this.pattern = Pattern.compile(re_string);
    }

    //Getters and Setters
    public void setMatcher(String to_match) {
        this.match_string = to_match;
        matcher = pattern.matcher(to_match);
    }

    //Functions

    /* Parse language into a series of FluffCalls
       to be stored in an ArrayList */
    public void translateLanguage() {
        //TODO: ensure no seg faults
        while (matcher.find()) {
            FluffCall find = new FluffCall(matcher.group(1), Integer.parseInt(matcher.group(2)));
            fluff_calls.add(find);
        }

        Pattern p = Pattern.compile("\\\\\\d([^<\\\\>]*)$");
        Matcher m = p.matcher(this.match_string);

        while (m.find()) {
            FluffCall find = new FluffCall(m.group(1));
            fluff_calls.add(find);
        }
    }

    /* Return String created from various FluffCalls based
       on 'fluffs' from language translation and 'calls' from
       given List */
    public String printWithFormat(List<String[]> matches) {
        String new_text = "";

        for(String[] groups : matches) {
            for(FluffCall flca : fluff_calls) {
                int i = flca.getCall();
                if(flca.hasCall() && i < groups.length) {
                    new_text = new_text + flca.getFluff() + groups[i];
                } else {
                    new_text += flca.getFluff();
                }
            }
            new_text += "\n";

        }

        return new_text;
    }


}
