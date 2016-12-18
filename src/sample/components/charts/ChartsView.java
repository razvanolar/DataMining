package sample.components.charts;

import javafx.scene.Node;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class ChartsView implements ChartsController.IChartsView {

  private BorderPane mainContainer;
  private TabPane tabPane;

  public ChartsView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    tabPane = new TabPane();
    mainContainer = new BorderPane(tabPane);
  }

  public TabPane getTabPane() {
    return tabPane;
  }

  @Override
  public Node asNode() {
    return mainContainer;
  }
}
