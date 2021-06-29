package org.saltfish.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.saltfish.setting.AppSettingsState;

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
        FileChooserDescriptor descriptor = new FileChooserDescriptor(true, false, false, true, false, false);
        VirtualFile virtualFile = FileChooser.chooseFile(descriptor,application,null);
        AppSettingsState appSettingsState = AppSettingsState.getInstance();
        appSettingsState.filePath = virtualFile.getPath();
    }
}