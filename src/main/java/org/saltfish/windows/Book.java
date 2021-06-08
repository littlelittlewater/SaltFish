package org.saltfish.windows;

import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;

public class Book {
    private JTextPane textPane1;
    private JPanel myWindowContent;

    public Book(ToolWindow toolWinows){
        textPane1.setText("hello World");
    }


    public JComponent getContent() {
        return myWindowContent;
    }
}
