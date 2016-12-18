package sample.components.edit_attributes_range;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class EditAttributesRangeView implements EditAttributesRangeController.IEditAttributesRangeView {

  private ScrollPane scrollPane;
  private VBox mainContainer;
  private Spinner<Number> adolescentMinSpinner;
  private Spinner<Number> adolescentMaxSpinner;
  private Spinner<Number> adultMinSpinner;
  private Spinner<Number> adultMaxSpinner;
  private Spinner<Number> midlifeMinSpinner;
  private Spinner<Number> midlifeMaxSpinner;
  private Spinner<Number> oldMinSpinner;
  private Spinner<Number> oldMaxSpinner;
  private Spinner<Number> heightShortMinSpinner;
  private Spinner<Number> heightShortMaxSpinner;
  private Spinner<Number> heightAverageMinSpinner;
  private Spinner<Number> heightAverageMaxSpinner;
  private Spinner<Number> heightTallMinSpinner;
  private Spinner<Number> heightTallMaxSpinner;
  private Spinner<Number> underweightMinSpinner;
  private Spinner<Number> underweightMaxSpinner;
  private Spinner<Number> healtyMinSpinner;
  private Spinner<Number> healtyMaxSpinner;
  private Spinner<Number> overweightMinSpinner;
  private Spinner<Number> overweightMaxSpinner;
  private Spinner<Number> obeseMinSpinner;
  private Spinner<Number> obeseMaxSpinner;
  private Spinner<Number> speedSlowMinSpinner;
  private Spinner<Number> speedSlowMaxSpinner;
  private Spinner<Number> speedAverageMinSpinner;
  private Spinner<Number> speedAverageMaxSpinner;
  private Spinner<Number> speedFastMinSpinner;
  private Spinner<Number> speedFastMaxSpinner;
  private Spinner<Number> distanceShortMinSpinner;
  private Spinner<Number> distanceShortMaxSpinner;
  private Spinner<Number> distanceAverageMinSpinner;
  private Spinner<Number> distanceAverageMaxSpinner;
  private Spinner<Number> distanceLongMinSpinner;
  private Spinner<Number> distanceLongMaxSpinner;

  public EditAttributesRangeView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    initComponents();
    mainContainer = new VBox(5);
    GridPane ageGridPane = createGridPane();
    GridPane heightGridPane = createGridPane();
    GridPane weightGridPane = createGridPane();
    GridPane averageSpeedGridPane = createGridPane();
    GridPane distanceGridPane = createGridPane();

    ageGridPane.add(new Label("Adolescent: "), 0, 0);
    ageGridPane.add(adolescentMinSpinner, 1, 0);
    ageGridPane.add(adolescentMaxSpinner, 2, 0);
    ageGridPane.add(new Label("Adult: "), 0, 1);
    ageGridPane.add(adultMinSpinner, 1, 1);
    ageGridPane.add(adultMaxSpinner, 2, 1);
    ageGridPane.add(new Label("Midlife: "), 0, 2);
    ageGridPane.add(midlifeMinSpinner, 1, 2);
    ageGridPane.add(midlifeMaxSpinner, 2, 2);
    ageGridPane.add(new Label("Old: "), 0, 3);
    ageGridPane.add(oldMinSpinner, 1, 3);
    ageGridPane.add(oldMaxSpinner, 2, 3);

    heightGridPane.add(new Label("Short: "), 0, 0);
    heightGridPane.add(heightShortMinSpinner, 1, 0);
    heightGridPane.add(heightShortMaxSpinner, 2, 0);
    heightGridPane.add(new Label("Average:    "), 0, 1);
    heightGridPane.add(heightAverageMinSpinner, 1, 1);
    heightGridPane.add(heightAverageMaxSpinner, 2, 1);
    heightGridPane.add(new Label("Tall: "), 0, 2);
    heightGridPane.add(heightTallMinSpinner, 1, 2);
    heightGridPane.add(heightTallMaxSpinner, 2, 2);

    weightGridPane.add(new Label("Underweight: "), 0, 0);
    weightGridPane.add(underweightMinSpinner, 1, 0);
    weightGridPane.add(underweightMaxSpinner, 2, 0);
    weightGridPane.add(new Label("Healty: "), 0, 1);
    weightGridPane.add(healtyMinSpinner, 1, 1);
    weightGridPane.add(healtyMaxSpinner, 2, 1);
    weightGridPane.add(new Label("Overweight: "), 0, 2);
    weightGridPane.add(overweightMinSpinner, 1, 2);
    weightGridPane.add(overweightMaxSpinner, 2, 2);
    weightGridPane.add(new Label("Obese: "), 0, 3);
    weightGridPane.add(obeseMinSpinner, 1, 3);
    weightGridPane.add(obeseMaxSpinner, 2, 3);

    averageSpeedGridPane.add(new Label("Slow: "), 0, 0);
    averageSpeedGridPane.add(speedSlowMinSpinner, 1, 0);
    averageSpeedGridPane.add(speedSlowMaxSpinner, 2, 0);
    averageSpeedGridPane.add(new Label("Average: "), 0, 1);
    averageSpeedGridPane.add(speedAverageMinSpinner, 1, 1);
    averageSpeedGridPane.add(speedAverageMaxSpinner, 2, 1);
    averageSpeedGridPane.add(new Label("Fast:"), 0, 2);
    averageSpeedGridPane.add(speedFastMinSpinner, 1, 2);
    averageSpeedGridPane.add(speedFastMaxSpinner, 2, 2);

    distanceGridPane.add(new Label("Short"), 0, 0);
    distanceGridPane.add(distanceShortMinSpinner, 1, 0);
    distanceGridPane.add(distanceShortMaxSpinner, 2, 0);
    distanceGridPane.add(new Label("Average: "), 0, 1);
    distanceGridPane.add(distanceAverageMinSpinner, 1, 1);
    distanceGridPane.add(distanceAverageMaxSpinner, 2, 1);
    distanceGridPane.add(new Label("Long: "), 0, 2);
    distanceGridPane.add(distanceLongMinSpinner, 1, 2);
    distanceGridPane.add(distanceLongMaxSpinner, 2, 2);

    mainContainer.getChildren().addAll(
            new Label("Age"),
            ageGridPane,
            new Label("Height"),
            heightGridPane,
            new Label("Weight"),
            weightGridPane,
            new Label("Average Speed"),
            averageSpeedGridPane,
            new Label("Distance"),
            distanceGridPane
    );

    mainContainer.setPadding(new Insets(15));

    scrollPane = new ScrollPane(mainContainer);
    scrollPane.prefHeight(400);
    scrollPane.setMaxHeight(400);
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
  }

  private GridPane createGridPane() {
    GridPane gridPane = new GridPane();
    gridPane.setAlignment(Pos.CENTER_LEFT);
    gridPane.setVgap(2);
    gridPane.setHgap(15);
    return gridPane;
  }

  private void initComponents() {
    adolescentMinSpinner = new Spinner<>(0, 0, 0, 0);
    adolescentMaxSpinner = new Spinner<>(1, 99, 18, 1);
    adultMinSpinner = new Spinner<>(1, 99, 18, 1);
    adultMaxSpinner = new Spinner<>(1, 99, 35, 1);
    midlifeMinSpinner = new Spinner<>(1, 99, 35, 1);
    midlifeMaxSpinner = new Spinner<>(1, 99, 50, 1);
    oldMinSpinner = new Spinner<>(1, 99, 50, 1);
    oldMaxSpinner = new Spinner<>(100, 100, 100, 0);

    heightShortMinSpinner = new Spinner<>(0.5, 0.5, 0.5, 0.5);
    heightShortMaxSpinner = new Spinner<>(0.6, 2.25, 1.6, 0.01);
    heightAverageMinSpinner = new Spinner<>(0.6, 2.25, 1.6, 0.01);
    heightAverageMaxSpinner = new Spinner<>(0.6, 2.25, 1.8, 0.01);
    heightTallMinSpinner = new Spinner<>(0.6, 2.25, 1.8, 0.01);
    heightTallMaxSpinner = new Spinner<>(2.3, 2.3, 2.3, 0);

    underweightMinSpinner = new Spinner<>(20, 20, 20, 0);
    underweightMaxSpinner = new Spinner<>(21, 175, 35, 1);
    healtyMinSpinner = new Spinner<>(21, 175, 40, 1);
    healtyMaxSpinner = new Spinner<>(21, 175, 70, 1);
    overweightMinSpinner = new Spinner<>(21, 175, 70, 1);
    overweightMaxSpinner = new Spinner<>(21, 175, 90, 1);
    obeseMinSpinner = new Spinner<>(21, 175, 90, 1);
    obeseMaxSpinner = new Spinner<>(180, 180, 180, 0);

    speedSlowMinSpinner = new Spinner<>(0, 0, 0, 0);
    speedSlowMaxSpinner = new Spinner<>(1, 50, 8, 0.1);
    speedAverageMinSpinner = new Spinner<>(1, 50, 8, 0.1);
    speedAverageMaxSpinner = new Spinner<>(1, 50, 16, 0.1);
    speedFastMinSpinner = new Spinner<>(1, 50, 16, 0.1);
    speedFastMaxSpinner = new Spinner<>(50, 50, 50, 0);

    distanceShortMinSpinner = new Spinner<>(0, 0, 0, 0);
    distanceShortMaxSpinner = new Spinner<>(1, 299, 10, 0.1);
    distanceAverageMinSpinner = new Spinner<>(1, 299, 10, 0.1);
    distanceAverageMaxSpinner = new Spinner<>(1, 299, 30, 0.1);
    distanceLongMinSpinner = new Spinner<>(1, 299, 30, 0.1);
    distanceLongMaxSpinner = new Spinner<>(300, 300, 300, 0);
  }

  @Override
  public Node asNode() {
    return scrollPane;
  }
}
