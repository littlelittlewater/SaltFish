package org.saltfish.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SaltFishAction extends AnAction {

    public SaltFishAction() {
        super("import book");
    }

    @Override
    public void update(AnActionEvent e) {
        // Using the event, evaluate the context, and enable or disable the action.
    }

    public void actionPerformed(AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        Project application = event.getProject();
        FileChooserDescriptor descriptor = new FileChooserDescriptor(true, false, false, true, false, true);
        VirtualFile[] virtualFiles = FileChooser.chooseFiles(descriptor, application, null);
        for (VirtualFile virtualFile : virtualFiles) {
            String name = virtualFile.getName();
            System.out.println("name = " + name);
            StringBuilder buffer = new StringBuilder();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(virtualFile.getInputStream()));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line);
                    buffer.append("\n");
                }
                System.out.println("buffer = " + buffer.toString());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}