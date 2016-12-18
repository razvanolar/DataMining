package sample.components.cluster_info_view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import sample.utils.enums.Distances;

public class ClusterInfoView implements ClusterInfoController.IClusterInfoView {

  private GridPane gridPane;
  private TextField tabNameTextField;
  private Spinner<Number> clusterNumberSpinner;
  private ComboBox<Distances> distancesComboBox;
  private CheckBox ageCheckBox;
  private CheckBox heightCheckBox;
  private CheckBox weightCheckBox;
  private CheckBox sexCheckBox;
  private CheckBox activityCheckBox;
  private CheckBox effortCheckBox;
  private CheckBox speedCheckBox;
  private CheckBox distanceCheckBox;

  public ClusterInfoView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    gridPane = new GridPane();
    tabNameTextField = new TextField();
    clusterNumberSpinner = new Spinner<>(2, 1000, 5, 1);
    distancesComboBox = new ComboBox<>();
    ageCheckBox = new CheckBox("Age");
    heightCheckBox = new CheckBox("Height");
    weightCheckBox = new CheckBox("Weight");
    sexCheckBox = new CheckBox("Sex");
    activityCheckBox = new CheckBox("Activity Domain");
    effortCheckBox = new CheckBox("Effort Level");
    speedCheckBox = new CheckBox("Average Speed");
    distanceCheckBox = new CheckBox("Distance");

    gridPane.add(new Label("Tab Name: "), 0, 0);
    gridPane.add(tabNameTextField, 1, 0);
    gridPane.add(new Label("Clusters: "), 0, 1);
    gridPane.add(clusterNumberSpinner, 1, 1);
    gridPane.add(new Label("Distance Formula: "), 0, 2);
    gridPane.add(distancesComboBox, 1, 2);
    gridPane.add(new Label("Attributes: "), 0, 3);
    gridPane.add(ageCheckBox, 1, 3);
    gridPane.add(heightCheckBox, 1, 4);
    gridPane.add(weightCheckBox, 1, 5);
    gridPane.add(sexCheckBox, 1, 6);
    gridPane.add(activityCheckBox, 1, 7);
    gridPane.add(effortCheckBox, 1, 8);
    gridPane.add(speedCheckBox, 1, 9);
    gridPane.add(distanceCheckBox, 1, 10);

    gridPane.setAlignment(Pos.CENTER);
    gridPane.setPadding(new Insets(15));
    gridPane.setVgap(5);
    gridPane.setHgap(10);

    distancesComboBox.getItems().addAll(Distances.values());
    distancesComboBox.setValue(Distances.EUCLIDEAN);
  }

  public TextField getTabNameTextField() {
    return tabNameTextField;
  }

  public Spinner<Number> getClusterNumberSpinner() {
    return clusterNumberSpinner;
  }

  public ComboBox<Distances> getDistancesComboBox() {
    return distancesComboBox;
  }

  public CheckBox getAgeCheckBox() {
    return ageCheckBox;
  }

  public CheckBox getHeightCheckBox() {
    return heightCheckBox;
  }

  public CheckBox getWeightCheckBox() {
    return weightCheckBox;
  }

  public CheckBox getSexCheckBox() {
    return sexCheckBox;
  }

  public CheckBox getActivityCheckBox() {
    return activityCheckBox;
  }

  public CheckBox getEffortCheckBox() {
    return effortCheckBox;
  }

  public CheckBox getSpeedCheckBox() {
    return speedCheckBox;
  }

  public CheckBox getDistanceCheckBox() {
    return distanceCheckBox;
  }

  @Override
  public Node asNode() {
    return gridPane;
  }
}
