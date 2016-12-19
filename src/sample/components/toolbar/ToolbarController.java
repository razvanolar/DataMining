package sample.components.toolbar;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import sample.components.MainController;
import sample.components.cluster_info_view.ClusterInfoController;
import sample.components.cluster_info_view.ClusterInfoView;
import sample.components.form.FormController;
import sample.components.form.FormView;
import sample.utils.enums.RepositoryTypes;
import sample.utils.interfaces.Controller;
import sample.utils.interfaces.View;
import sample.utils.enums.MainContentTypes;
import sample.utils.views.OkCancelDialog;
import sample.utils.views.ToggleSwitch;

public class ToolbarController implements Controller<ToolbarController.IToolbarView> {

  public interface IToolbarView extends View {
    ToggleSwitch getToggleSwitch();
    Button getRefreshDataButton();
    Button getFormButton();
    Button getClusteringButton();
    ToggleButton getChartsButton();
    ToggleButton getDataSetButton();
    ToggleButton getLogButton();
  }

  private IToolbarView view;

  @Override
  public void bind(IToolbarView view) {
    this.view = view;
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

    view.getToggleSwitch().switchOnProperty().addListener((observable, oldValue, newValue) -> changeRepository(newValue));
    view.getRefreshDataButton().setOnAction(event -> onRefreshSelection());
    view.getFormButton().setOnAction(event -> onFormButtonSelection());
    view.getClusteringButton().setOnAction(event -> onClusterButtonSelection());
  }

  public void changeToChartView() {
    view.getChartsButton().setSelected(true);
  }

  /**
   * Change the used repository
   * @param value - true to use FileRepository
   *              - false to use JDBCRepository
   */
  private void changeRepository(boolean value) {
    if (value) {
      MainController.getInstance().load(RepositoryTypes.FILE);
    } else
      MainController.getInstance().load(RepositoryTypes.DATABASE);

  }

  private void onRefreshSelection() {
    changeRepository(view.getToggleSwitch().switchOnProperty().getValue());
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

  private void onClusterButtonSelection() {
    OkCancelDialog dialog = new OkCancelDialog("Clustering Data", StageStyle.UTILITY, Modality.APPLICATION_MODAL,
            true, true);
    ClusterInfoController clusterInfoController = new ClusterInfoController(dialog.getOkButton());
    ClusterInfoController.IClusterInfoView iClusterInfoView = new ClusterInfoView();
    clusterInfoController.bind(iClusterInfoView);
    dialog.setContent(iClusterInfoView.asNode());
    dialog.getOkButton().setOnAction(event -> {
      if (clusterInfoController.isValid()) {
        dialog.close();
        MainController.getInstance().applyClustering(clusterInfoController.getTabName(),
                clusterInfoController.getClustersNumber(),
                clusterInfoController.getFormula(),
                clusterInfoController.getMask());
      }
    });
    dialog.show();
  }
}
