package sample.components;

import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import sample.components.data_set_view.DataSetController;
import sample.components.data_set_view.DataSetView;
import sample.components.charts.ChartsController;
import sample.components.charts.ChartsView;
import sample.components.logs_view.LogView;
import sample.components.logs_view.LogsController;
import sample.components.menu.MenuBarController;
import sample.components.menu.MenuBarView;
import sample.components.toolbar.ToolbarController;
import sample.components.toolbar.ToolbarView;
import sample.utils.interfaces.View;
import sample.utils.enums.MainContentTypes;

public class MainView implements View {

  private BorderPane mainContainer;
  private DataSetController.IDataSetView iDataSetView;
  private ChartsController.IChartsView iChartsView;
  private LogsController.ILogView iLogView;

  public MainView() {
    MainController.getInstance().setMainView(this);
    initGUI();
  }

  @Override
  public void initGUI() {
    MenuBarController menuBarController = new MenuBarController();
    ToolbarController toolbarController = new ToolbarController();
    DataSetController dataSetController = new DataSetController();
    ChartsController chartsController = new ChartsController();
    LogsController logsController = new LogsController();

    MenuBarController.IMenuBarView iMenuBarView = new MenuBarView();
    ToolbarController.IToolbarView iToolbarView = new ToolbarView();
    iDataSetView = new DataSetView();
    iChartsView = new ChartsView();
    iLogView = new LogView();

    menuBarController.bind(iMenuBarView);
    toolbarController.bind(iToolbarView);
    dataSetController.bind(iDataSetView);
    chartsController.bind(iChartsView);
    logsController.bind(iLogView);

    mainContainer = new BorderPane();
    VBox vBox = new VBox();
    vBox.getChildren().add(iMenuBarView.asNode());
    vBox.getChildren().add(iToolbarView.asNode());
    mainContainer.setTop(vBox);

    mainContainer.setCenter(iDataSetView.asNode());

    HBox footer = new HBox();
    footer.setPrefSize(0, 20);
    footer.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
    mainContainer.setBottom(footer);
  }

  public void setCenterContent(MainContentTypes contentType) {
    if (contentType == MainContentTypes.DATA_SET)
      mainContainer.setCenter(iDataSetView.asNode());
    else if (contentType == MainContentTypes.FORM)
      mainContainer.setCenter(iChartsView.asNode());
    else
      mainContainer.setCenter(iLogView.asNode());
  }

  @Override
  public Node asNode() {
    return mainContainer;
  }
}
