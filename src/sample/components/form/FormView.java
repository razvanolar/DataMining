package sample.components.form;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import sample.utils.enums.Activities;

public class FormView implements FormController.IFormView {

  private GridPane gridPane;
  private DatePicker birthDatePicker;
  private Spinner<Number> heightSpinner;
  private Spinner<Number> weightSpinner;
  private RadioButton masculineSexButton;
  private ComboBox<Activities> activitiesComboBox;
  private RadioButton oneEffortLevelButton;
  private RadioButton twoEffortLevelButton;
  private RadioButton threeEffortLevelButton;
  private RadioButton fourEffortLevelButton;
  private RadioButton fiveEffortLevelButton;
  private Spinner<Number> averageSpeedSpinner;
  private Label resultLabel;

  public FormView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    gridPane = new GridPane();
    birthDatePicker = new DatePicker();
    heightSpinner = new Spinner<>(1.35, 2.30, 1.75, 0.01);
    weightSpinner = new Spinner<>(35, 180, 65, 1);
    masculineSexButton = new RadioButton("Male");
    RadioButton feminineSexButton = new RadioButton("Female");
    activitiesComboBox = new ComboBox<>();
    oneEffortLevelButton = new RadioButton("1");
    twoEffortLevelButton = new RadioButton("2");
    threeEffortLevelButton = new RadioButton("3");
    fourEffortLevelButton = new RadioButton("4");
    fiveEffortLevelButton = new RadioButton("5");
    HBox effortLevelPane = new HBox(6, oneEffortLevelButton, twoEffortLevelButton, threeEffortLevelButton,
            fourEffortLevelButton, fiveEffortLevelButton);
    averageSpeedSpinner = new Spinner<>(4, 50, 10, 0.1);
    resultLabel = new Label("----");

    gridPane.add(new Label("Date of birth: "), 0, 0);
    gridPane.add(birthDatePicker, 1, 0, 2, 1);
    gridPane.add(new Label("Height"), 0, 1);
    gridPane.add(heightSpinner, 1, 1, 2, 1);
    gridPane.add(new Label("Weight"), 0, 2);
    gridPane.add(weightSpinner, 1, 2, 2, 1);
    gridPane.add(new Label("Sex: "), 0, 3);
    gridPane.add(masculineSexButton, 1, 3);
    gridPane.add(feminineSexButton, 2, 3);
    gridPane.add(new Label("Activity Domain: "), 0, 4);
    gridPane.add(activitiesComboBox, 1, 4, 2, 1);
    gridPane.add(new Label("Effort Level: "), 0, 5);
    gridPane.add(effortLevelPane, 1, 5, 2, 1);
    gridPane.add(new Label("Average Speed: "), 0, 6);
    gridPane.add(averageSpeedSpinner, 1, 6, 2, 1);
    gridPane.add(new Label("Result: "), 0, 7);
    gridPane.add(resultLabel, 1, 7, 2, 1);

    gridPane.setVgap(10);
    gridPane.setPadding(new Insets(15));
    gridPane.setAlignment(Pos.CENTER);

    activitiesComboBox.getItems().addAll(Activities.values());
    activitiesComboBox.getSelectionModel().select(Activities.PROGRAMMMER);
    effortLevelPane.setAlignment(Pos.CENTER);

    ToggleGroup effortLevelGroup = new ToggleGroup();
    effortLevelGroup.getToggles().addAll(
            oneEffortLevelButton,
            twoEffortLevelButton,
            threeEffortLevelButton,
            fourEffortLevelButton,
            fiveEffortLevelButton
    );
    threeEffortLevelButton.selectedProperty().setValue(true);

    ToggleGroup sexGroup = new ToggleGroup();
    sexGroup.getToggles().addAll(masculineSexButton, feminineSexButton);
    masculineSexButton.selectedProperty().set(true);
  }

  public DatePicker getBirthDatePicker() {
    return birthDatePicker;
  }

  public Spinner<Number> getHeightSpinner() {
    return heightSpinner;
  }

  public Spinner<Number> getWeightSpinner() {
    return weightSpinner;
  }

  public ComboBox<Activities> getActivitiesComboBox() {
    return activitiesComboBox;
  }

  public Spinner<Number> getAverageSpeedSpinner() {
    return averageSpeedSpinner;
  }

  public int getSelectedSex() {
    if (masculineSexButton.isSelected())
      return 1;
    return 0;
  }

  public int getSelectedEffortLevel() {
    if (oneEffortLevelButton.isSelected())
      return 1;
    if (twoEffortLevelButton.isSelected())
      return 2;
    if (threeEffortLevelButton.isSelected())
      return 3;
    if (fourEffortLevelButton.isSelected())
      return 4;
    if (fiveEffortLevelButton.isSelected())
      return 5;
    return 1;
  }

  public Label getResultLabel() {
    return resultLabel;
  }

  @Override
  public Node asNode() {
    return gridPane;
  }
}
