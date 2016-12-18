package sample.components.cluster_info_view;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import sample.utils.enums.Distances;
import sample.utils.interfaces.Controller;
import sample.utils.interfaces.View;

public class ClusterInfoController implements Controller<ClusterInfoController.IClusterInfoView> {

  public interface IClusterInfoView extends View {
    TextField getTabNameTextField();
    Spinner<Number> getClusterNumberSpinner();
    ComboBox<Distances> getDistancesComboBox();
    CheckBox getAgeCheckBox();
    CheckBox getHeightCheckBox();
    CheckBox getWeightCheckBox();
    CheckBox getSexCheckBox();
    CheckBox getActivityCheckBox();
    CheckBox getEffortCheckBox();
    CheckBox getSpeedCheckBox();
    CheckBox getDistanceCheckBox();
  }

  private IClusterInfoView view;
  private Button actionButton;

  public ClusterInfoController(Button actionButton) {
    this.actionButton = actionButton;
  }

  @Override
  public void bind(IClusterInfoView view) {
    this.view = view;
    this.actionButton.disableProperty().setValue(true);

    view.getTabNameTextField().textProperty().addListener((observable, oldValue, newValue) -> this.actionButton.setDisable(!isValid()));
    ChangeListener<Boolean> listener = (observable, oldValue, newValue) -> this.actionButton.setDisable(!isValid());
    view.getAgeCheckBox().selectedProperty().addListener(listener);
    view.getHeightCheckBox().selectedProperty().addListener(listener);
    view.getWeightCheckBox().selectedProperty().addListener(listener);
    view.getSexCheckBox().selectedProperty().addListener(listener);
    view.getActivityCheckBox().selectedProperty().addListener(listener);
    view.getEffortCheckBox().selectedProperty().addListener(listener);
    view.getSpeedCheckBox().selectedProperty().addListener(listener);
    view.getDistanceCheckBox().selectedProperty().addListener(listener);
  }

  public boolean isValid() {
    return view.getTabNameTextField().getText() != null &&
            !view.getTabNameTextField().getText().isEmpty() &&
            atLeastTwoAttributesSelected();
  }

  private boolean atLeastTwoAttributesSelected() {
    int total = 0;
    if (view.getAgeCheckBox().isSelected())
      total ++;
    if (view.getHeightCheckBox().isSelected())
      total ++;
    if (view.getWeightCheckBox().isSelected())
      total ++;
    if (view.getSexCheckBox().isSelected())
      total ++;
    if (view.getActivityCheckBox().isSelected())
      total ++;
    if (view.getEffortCheckBox().isSelected())
      total ++;
    if (view.getSpeedCheckBox().isSelected())
      total ++;
    if (view.getDistanceCheckBox().isSelected())
      total ++;
    return total >= 2;
  }
}
