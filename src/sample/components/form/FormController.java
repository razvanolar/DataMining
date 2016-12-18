package sample.components.form;

import javafx.scene.control.*;
import sample.utils.enums.Activities;
import sample.utils.interfaces.Controller;
import sample.utils.interfaces.View;

public class FormController implements Controller<FormController.IFormView> {

  public interface IFormView extends View {
    DatePicker getBirthDatePicker();
    Spinner<Number> getHeightSpinner();
    Spinner<Number> getWeightSpinner();
    ComboBox<Activities> getActivitiesComboBox();
    Spinner<Number> getAverageSpeedSpinner();
    int getSelectedSex();
    int getSelectedEffortLevel();
    Label getResultLabel();
  }

  private Button actionButton;

  public FormController(Button actionButton) {
    this.actionButton = actionButton;
  }

  @Override
  public void bind(IFormView view) {
    actionButton.setOnAction(event -> {

    });
  }
}
