package sample.components.toolbar;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import sample.utils.FillToolItem;

public class ToolbarView implements ToolbarController.IToolbarView {

  private ToolBar toolBar;
  private Button formButton;
  private ToggleButton dataSetButton;
  private ToggleButton chartsButton;
  private ToggleButton logButton;
  private Button clusteringButton;

  public ToolbarView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    formButton = new Button("Form");
    clusteringButton = new Button("Cluster");
    dataSetButton = new ToggleButton("Data Set");
    chartsButton = new ToggleButton("Charts");
    logButton = new ToggleButton("Rules");
    toolBar = new ToolBar(
            formButton,
            clusteringButton,
            new FillToolItem(),
            dataSetButton,
            chartsButton,
            logButton
    );

    ToggleGroup group = new ToggleGroup();
    group.getToggles().addAll(dataSetButton, chartsButton, logButton);
    dataSetButton.setSelected(true);
  }

  public Button getFormButton() {
    return formButton;
  }

  public Button getClusteringButton() {
    return clusteringButton;
  }

  public ToggleButton getChartsButton() {
    return chartsButton;
  }

  public ToggleButton getDataSetButton() {
    return dataSetButton;
  }

  public ToggleButton getLogButton() {
    return logButton;
  }

  @Override
  public Node asNode() {
    return toolBar;
  }
}
