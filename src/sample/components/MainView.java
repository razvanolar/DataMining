package sample.components;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import sample.components.data_set_view.DataSetController;
import sample.components.data_set_view.DataSetView;
import sample.components.charts.ChartsController;
import sample.components.charts.ChartsView;
import sample.components.logs_view.RuleView;
import sample.components.logs_view.RuleController;
import sample.components.menu.MenuBarController;
import sample.components.menu.MenuBarView;
import sample.components.toolbar.ToolbarController;
import sample.components.toolbar.ToolbarView;
import sample.utils.interfaces.View;
import sample.utils.enums.MainContentTypes;

public class MainView implements View {

  private StackPane stackPane;
  private BorderPane maskPane;
  private Label maskLabel;
  private BorderPane mainContainer;
  private DataSetController.IDataSetView iDataSetView;
  private ChartsController.IChartsView iChartsView;
  private RuleController.IRuleView iRuleView;

  public MainView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    MenuBarController menuBarController = new MenuBarController();
    ToolbarController toolbarController = new ToolbarController();
    DataSetController dataSetController = new DataSetController();
    ChartsController chartsController = new ChartsController();
    RuleController ruleController = new RuleController();

    MenuBarController.IMenuBarView iMenuBarView = new MenuBarView();
    ToolbarController.IToolbarView iToolbarView = new ToolbarView();
    iDataSetView = new DataSetView();
    iChartsView = new ChartsView();
    iRuleView = new RuleView();

    menuBarController.bind(iMenuBarView);
    toolbarController.bind(iToolbarView);
    dataSetController.bind(iDataSetView);
    chartsController.bind(iChartsView);
    ruleController.bind(iRuleView);

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

    stackPane = new StackPane(mainContainer);
    GridPane panel = new GridPane();
    maskPane = new BorderPane(panel);
    panel.setAlignment(Pos.CENTER);
    maskLabel = new Label("");
    MainController.getInstance().inject(this, dataSetController, toolbarController, chartsController, ruleController);
  }

  public void mask(String message) {
    maskLabel.setText(message);
    if (!stackPane.getChildren().contains(maskPane))
      stackPane.getChildren().add(maskPane);
  }

  public void unmask() {
    if (stackPane.getChildren().contains(maskPane))
      stackPane.getChildren().remove(maskPane);
  }

  public void setCenterContent(MainContentTypes contentType) {
    if (contentType == MainContentTypes.DATA_SET)
      mainContainer.setCenter(iDataSetView.asNode());
    else if (contentType == MainContentTypes.FORM)
      mainContainer.setCenter(iChartsView.asNode());
    else
      mainContainer.setCenter(iRuleView.asNode());
  }

  @Override
  public Node asNode() {
    return stackPane;
  }
}
