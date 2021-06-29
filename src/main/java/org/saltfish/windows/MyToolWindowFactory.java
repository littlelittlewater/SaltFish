package org.saltfish.windows;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.sun.istack.NotNull;

import java.time.LocalDateTime;

public class MyToolWindowFactory implements ToolWindowFactory {
  private static Book book;
  public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
    book = new Book(toolWindow);
    ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
    Content content = contentFactory.createContent(book.getContent(), "kkkkkkk", true);
    toolWindow.getContentManager().addContent(content);
  }

  public static void addText(){
      book.updateCount("hello" + LocalDateTime.now());
  }

}