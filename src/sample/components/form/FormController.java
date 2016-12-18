package sample.components.form;

import javafx.scene.control.*;
import sample.components.MainController;
import sample.models.FormattedEntry;
import sample.utils.enums.Activities;
import sample.utils.interfaces.Controller;
import sample.utils.interfaces.View;

import java.time.LocalDate;
import java.util.Calendar;

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
      LocalDate selectedDate = view.getBirthDatePicker().getValue();
      Calendar now = Calendar.getInstance();   // Gets the current date and time
      int year = now.get(Calendar.YEAR);
      String age = (year - selectedDate.getYear()) + "";
      String height = view.getHeightSpinner().getValue().toString();
      String weight = view.getWeightSpinner().getValue().toString();
      String sex = view.getSelectedSex() + "";
      String activityDomain = view.getActivitiesComboBox().getValue().getId() + "";
      String effortLevel = view.getSelectedEffortLevel() + "";
      String averageSpeed = view.getAverageSpeedSpinner().getValue().toString();
      FormattedEntry entry = new FormattedEntry(age + "," + height + "," + weight + "," + sex + "," + activityDomain + "," + effortLevel + "," + averageSpeed + ",0");
      float result = MainController.getInstance().getRepo().getTree().find(entry);
      if (result == -1) {
        view.getResultLabel().setText("No available value");
      } else {
        view.getResultLabel().setText(result + " km");
      }
    });
    actionButton.setDisable(true);
    view.getBirthDatePicker().valueProperty().addListener((observable, oldValue, newValue) -> actionButton.setDisable(newValue == null));
    view.getBirthDatePicker().setValue(LocalDate.of(1996, 1, 1));
  }
}
