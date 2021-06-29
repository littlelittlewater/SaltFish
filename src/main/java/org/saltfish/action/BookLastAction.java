package org.saltfish.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.saltfish.windows.MyToolWindowFactory;

public class BookLastAction extends AnAction {

    public BookLastAction() {
        super("book next");
    }

    @Override
    public void update(AnActionEvent e) {
        // Using the event, evaluate the context, and enable or disable the action.
    }

    public void actionPerformed(AnActionEvent event) {
        MyToolWindowFactory.lastText();
        System.out.println("click the book next Line");
    }
}