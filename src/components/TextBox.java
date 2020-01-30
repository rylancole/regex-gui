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

public class TextBox {

    private JTextArea input_field;
    private JScrollPane scroll;

    public TextBox() {
        input_field = new JTextArea(20, 50);
        input_field.setLineWrap(true);

        //TODO: allow dropped files to input commands
        //TODO: fix warnings
        input_field.setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
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

        scroll = new JScrollPane(input_field);
    }

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
