package sample.components.logs_view;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class LogView implements LogsController.ILogView {

  private BorderPane mainContainer;

  public LogView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    mainContainer = new BorderPane(new Label("Log View"));
  }

  @Override
  public Node asNode() {
    return mainContainer;
  }
}
