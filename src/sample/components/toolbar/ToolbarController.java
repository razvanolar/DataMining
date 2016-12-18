package sample.components.toolbar;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import sample.components.MainController;
import sample.components.form.FormController;
import sample.components.form.FormView;
import sample.utils.interfaces.Controller;
import sample.utils.interfaces.View;
import sample.utils.enums.MainContentTypes;
import sample.utils.views.OkCancelDialog;

public class ToolbarController implements Controller<ToolbarController.IToolbarView> {

  public interface IToolbarView extends View {
    Button getFormButton();
    ToggleButton getChartsButton();
    ToggleButton getDataSetButton();
    ToggleButton getLogButton();
  }

  @Override
  public void bind(IToolbarView view) {
    ChangeListener<Boolean> listener = (observable, oldValue, newValue) -> {
      if (!newValue)
        return;
      BooleanProperty booleanProperty = (BooleanProperty) observable;
      ToggleButton button = (ToggleButton) booleanProperty.getBean();
      if (button == view.getDataSetButton())
        MainController.getInstance().setMainContentType(MainContentTypes.DATA_SET);
      else if (button == view.getChartsButton())
        MainController.getInstance().setMainContentType(MainContentTypes.FORM);
      else
        MainController.getInstance().setMainContentType(MainContentTypes.LOGS);
    };
    view.getDataSetButton().selectedProperty().addListener(listener);
    view.getChartsButton().selectedProperty().addListener(listener);
    view.getLogButton().selectedProperty().addListener(listener);

    view.getFormButton().setOnAction(event -> onFormButtonSelection());
  }

  private void onFormButtonSelection() {
    OkCancelDialog dialog = new OkCancelDialog("Find Average Distance", StageStyle.UTILITY, Modality.APPLICATION_MODAL,
            true, 450, 400);
    FormController formController = new FormController(dialog.getOkButton());
    FormController.IFormView iFormView = new FormView();
    formController.bind(iFormView);
    dialog.setContent(iFormView.asNode());
    dialog.getOkButton().setText("Find Rule");
    dialog.show();
  }
}
