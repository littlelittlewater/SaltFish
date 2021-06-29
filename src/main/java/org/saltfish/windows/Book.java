package org.saltfish.windows;

import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.wm.ToolWindow;
import org.saltfish.setting.AppSettingsState;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private JTextPane textPane1;
    private JPanel myWindowContent;
    private String filePath;
    private RandomAccessFile randomAccessFile;
    private List<String> formatString = new ArrayList<>();
    private int cursor = 0;

    public Book(ToolWindow toolWinows) {
        textPane1.setText("hello World");
        freshFile();
    }

    public void updateCount(String content) {
        textPane1.setText(content);
    }

    public JComponent getContent() {
        return myWindowContent;
    }

    public void changeFile() {

    }

    public void nextPage() {
        cursor = Math.min(++cursor, formatString.size() - 1);
        textPane1.setText(formatString.get(cursor));
    }

    public void lastPage() {
        cursor = Math.max(--cursor, 0);
        textPane1.setText(formatString.get(cursor));
    }


    public void freshFile() {
        filePath = AppSettingsState.getInstance().filePath;
        int height = AppSettingsState.getInstance().height;
        int length = AppSettingsState.getInstance().length;
        formatString = new ArrayList<>();

        try {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            randomAccessFile = new RandomAccessFile(filePath, "r");
            // read all bytes
            StringBuilder memoryText = new StringBuilder();
            String temp = randomAccessFile.readLine();
            while (temp != null) {
                memoryText.append(new String(temp.getBytes("ISO-8859-1"), "UTF-8"));
                temp = randomAccessFile.readLine();
            }

            //format
            for (int an = 0; an < memoryText.length(); ) {
                StringBuilder page = new StringBuilder("");
                for (int i = 0; i < height && an < memoryText.length(); i++) {
                    for (int j = 0; j < length && an < memoryText.length(); j++) {
                        if (memoryText.charAt(an) == '\n') {
                            break;
                        } else {
                            page.append(memoryText.charAt(an++));
                        }
                    }
                    page.append('\n');
                }
                formatString.add(page.toString());
            }

            //reset cursor
            cursor = 0;
            textPane1.setText(formatString.get(0));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
