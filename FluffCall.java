import javax.swing.*;
import java.awt.*;

public class FluffCall {

    private String fluff;
    private int call = -1;

    public FluffCall(String fluff, int call) {
        this.fluff = fluff;
        this.call = call;
    }

    public FluffCall(String fluff) {
        this.fluff = fluff;
    }

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

    public boolean hasCall() {
        if(this.call != -1) {
            return true;
        }

        return false;
    }






}
