package sample.components.form;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;

public class FormView implements FormController.IFormView {

  private GridPane gridPane;
  private DatePicker birthDatePicker;
  private Spinner<Number> heightSpinner;
  private Spinner<Number> weightSpinner;

  public FormView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    gridPane = new GridPane();
    birthDatePicker = new DatePicker();
    heightSpinner = new Spinner<>(1.35, 2.30, 1.75, 0.01);
    weightSpinner = new Spinner<>(35, 180, 65, 1);

    gridPane.add(new Label("Date of birth: "), 0, 0);
    gridPane.add(birthDatePicker, 1, 0);
    gridPane.add(new Label("Height"), 0, 1);
    gridPane.add(heightSpinner, 1, 1);
    gridPane.add(new Label("Weight"), 0, 2);
    gridPane.add(weightSpinner, 1, 2);

    gridPane.setVgap(5);
    gridPane.setPadding(new Insets(5));
    gridPane.setAlignment(Pos.CENTER);
  }

  @Override
  public Node asNode() {
    return gridPane;
  }
}
