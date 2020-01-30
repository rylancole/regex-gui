package components;

import javax.swing.*;
import java.awt.*;

/*
 * FluffCall
 *  -Object utilized for formating Strings.
 *  -Holds a 'fluff' variable that prints before a given
 *  String, which is called from an ArrayList given the
 *  index 'call'
 *  -A 'call' is any backslash (\) followed by a single digit
 *  -A 'fluff' is a String which precedes a 'call' or end of string
 */

public class FluffCall {

    private String fluff;
    private int call = -1;

    //Constructors
    public FluffCall(String fluff, int call) {
        this.fluff = fluff;
        this.call = call;
    }

    public FluffCall(String fluff) {
        this.fluff = fluff;
    }

    //Getters and Setters
    public void setFluff(String fluff) {
        this.fluff = fluff;
    }

    public String getFluff() {
        return this.fluff;
    }

    public void setCall(int call) {
        this.call = call;
    }

    public int getCall() {
        return this.call;
    }

    //Functions

    /* Return True if call variable has been set */
    public boolean hasCall() {
        if(this.call != -1) {
            return true;
        }
        return false;
    }






}
