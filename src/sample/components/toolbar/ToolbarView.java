package sample.components.toolbar;

import javafx.scene.Node;
import javafx.scene.control.*;
import sample.utils.FillToolItem;
import sample.utils.views.ToggleSwitch;

public class ToolbarView implements ToolbarController.IToolbarView {

  private ToolBar toolBar;
  private ToggleSwitch toggleSwitch;
  private Button refreshDataButton;
  private Button formButton;
  private ToggleButton rawEntriesToggleButton;
  private ToggleButton formattedEntriesToggleButton;
  private ToggleButton dataSetButton;
  private ToggleButton chartsButton;
  private ToggleButton logButton;
  private Button clusteringButton;

  public ToolbarView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    toggleSwitch = new ToggleSwitch();
    refreshDataButton = new Button("Refresh");
    formButton = new Button("Form");
    rawEntriesToggleButton = new ToggleButton("Raw");
    formattedEntriesToggleButton = new ToggleButton("Formatted");
    clusteringButton = new Button("Cluster");
    dataSetButton = new ToggleButton("Data Set");
    chartsButton = new ToggleButton("Charts");
    logButton = new ToggleButton("Rules");
    toolBar = new ToolBar(
            toggleSwitch,
            refreshDataButton,
            formButton,
            new Separator(),
            rawEntriesToggleButton,
            formattedEntriesToggleButton,
            new Separator(),
            clusteringButton,
            new FillToolItem(),
            dataSetButton,
            chartsButton,
            logButton
    );

    ToggleGroup group = new ToggleGroup();
    group.getToggles().addAll(dataSetButton, chartsButton, logButton);
    ToggleGroup entriesGroup = new ToggleGroup();
    entriesGroup.getToggles().addAll(rawEntriesToggleButton, formattedEntriesToggleButton);
    dataSetButton.setSelected(true);
    rawEntriesToggleButton.setSelected(true);

    toolBar.getStyleClass().add("clearButtonsContainer");
  }

  public ToggleSwitch getToggleSwitch() {
    return toggleSwitch;
  }

  public Button getRefreshDataButton() {
    return refreshDataButton;
  }

  public Button getFormButton() {
    return formButton;
  }

  public ToggleButton getRawEntriesToggleButton() {
    return rawEntriesToggleButton;
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
