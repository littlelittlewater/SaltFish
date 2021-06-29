package org.saltfish.setting;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;
import org.saltfish.windows.MyToolWindowFactory;

import javax.swing.*;

public class AppSettingsConfigurable implements Configurable {

    private AppSettingsComponent mySettingsComponent;

    // A default constructor with no arguments is required because this implementation
    // is registered as an applicationConfigurable EP

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "SDK: Application Settings Example";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return mySettingsComponent.getPreferredFocusedComponent();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        mySettingsComponent = new AppSettingsComponent();
        return mySettingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        AppSettingsState settings = AppSettingsState.getInstance();
        boolean modified = !mySettingsComponent.getUserNameText().equals(settings.filePath);
        modified |= mySettingsComponent.getIdeaUserStatus() != settings.ideaStatus;
        modified |= mySettingsComponent.getHeight() != settings.height;
        modified |= mySettingsComponent.getLength() != settings.length;
        return modified;
    }

    @Override
    public void apply() {
        AppSettingsState settings = AppSettingsState.getInstance();
        settings.filePath = mySettingsComponent.getUserNameText();
        settings.ideaStatus = mySettingsComponent.getIdeaUserStatus();
        settings.length = mySettingsComponent.getLength();
        settings.height = mySettingsComponent.getHeight();
        MyToolWindowFactory.freshFile();
    }

    @Override
    public void reset() {
        AppSettingsState settings = AppSettingsState.getInstance();
        mySettingsComponent.setUserNameText(settings.filePath);
        mySettingsComponent.setIdeaUserStatus(settings.ideaStatus);
        mySettingsComponent.setHeight(settings.height);
        mySettingsComponent.setLength(settings.length);
    }

    @Override
    public void disposeUIResources() {
        mySettingsComponent = null;
    }

}