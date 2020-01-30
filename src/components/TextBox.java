package components;

import javax.swing.*;
import java.awt.*;

import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DnDConstants;
import java.awt.datatransfer.DataFlavor;
import java.util.List;
import java.util.Scanner;
import java.io.File;

/*
 * TextBox
 *  -Object utilized for text input
 *  -Can be written into, or have a file dragged
 *  and dropped onto it to write out file's text
 *  -Line wrapping and scrolling enabled
 */

public class TextBox {

    private JTextArea input_field;
    private JScrollPane scroll;

    //Constructor
    public TextBox() {
        //Create text area with line wrap enabled
        input_field = new JTextArea(20, 50);
        input_field.setLineWrap(true);

        //Activate ability to have file drag and drop
        //Shamelessly stolen from StackOverflow
        input_field.setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    //TODO: allow dropped files to input commands
                    //TODO: fix warnings
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List<File>)
                        evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    for (File file : droppedFiles) {
                        Scanner fileReader = new Scanner(file);
                        String data = "";
                        while (fileReader.hasNextLine()) {
                            data = data + fileReader.nextLine() + "\n";
                        }
                        fileReader.close();
                        input_field.setText(data);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        //Enable scrolling and complete creation TextBox object
        scroll = new JScrollPane(input_field);
    }

    //Getters and Setters
    public JScrollPane getScroll() {
        return this.scroll;
    }

    public void setText(String text) {
        this.input_field.setText(text);
    }

    public String getText() {
        return this.input_field.getText();
    }

}
