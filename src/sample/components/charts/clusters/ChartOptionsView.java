package sample.components.charts.clusters;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.Chart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import sample.utils.interfaces.View;

import java.util.List;

public class ChartOptionsView implements View {

  private List<String> options;
  private BorderPane mainContainer;
  private Chart chart;
  private Button applyButton;
  private RadioButton scatterRadioButton;
  private RadioButton piRadioButton;
  private ComboBox<String> xComboBox;
  private ComboBox<String> yComboBox;

  public ChartOptionsView(Chart chart, List<String> options) {
    this.chart = chart;
    this.options = options;
    initGUI();
  }

  @Override
  public void initGUI() {
    GridPane gridPane = new GridPane();
    applyButton = new Button("Apply");
    scatterRadioButton = new RadioButton("Scatter Chart");
    piRadioButton = new RadioButton("Pie Chart");
    xComboBox = new ComboBox<>();
    yComboBox = new ComboBox<>();
    mainContainer = new BorderPane(chart);

    gridPane.add(new Label("X: "), 0, 0);
    gridPane.add(xComboBox, 1, 0);
    gridPane.add(scatterRadioButton, 2, 0);
    gridPane.add(new Label("Y"), 0, 1);
    gridPane.add(yComboBox, 1, 1);
    gridPane.add(piRadioButton, 2, 1);
    gridPane.add(applyButton, 3, 1);

    mainContainer.setTop(gridPane);
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setPadding(new Insets(5));
    gridPane.setVgap(3);
    gridPane.setHgap(10);
    xComboBox.getItems().addAll(options);
    yComboBox.getItems().addAll(options);
    xComboBox.setValue(options.get(0));
    yComboBox.setValue(options.get(1));
    ToggleGroup group = new ToggleGroup();
    group.getToggles().addAll(scatterRadioButton, piRadioButton);
    scatterRadioButton.setSelected(true);
    scatterRadioButton.setSelected(true);
  }

  public void setChart(Chart chart) {
    this.chart = chart;
    mainContainer.setCenter(chart);
  }

  public Button getApplyButton() {
    return applyButton;
  }

  public RadioButton getScatterRadioButton() {
    return scatterRadioButton;
  }

  public ComboBox<String> getxComboBox() {
    return xComboBox;
  }

  public ComboBox<String> getyComboBox() {
    return yComboBox;
  }

  @Override
  public Node asNode() {
    return mainContainer;
  }
}
