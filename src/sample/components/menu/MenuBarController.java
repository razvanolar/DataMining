package sample.components.menu;

import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import sample.components.MainController;
import sample.components.edit_attributes_range.EditAttributesRangeController;
import sample.components.edit_attributes_range.EditAttributesRangeView;
import sample.components.edit_entries_path.EditEntriesPathController;
import sample.components.edit_entries_path.EditEntriesPathView;
import sample.utils.interfaces.Controller;
import sample.utils.interfaces.View;
import sample.utils.views.OkCancelDialog;

public class MenuBarController implements Controller<MenuBarController.IMenuBarView> {

  public interface IMenuBarView extends View {
    MenuItem getEditAttributesRangeMenuItem();
    MenuItem getEditEntriesFilePathMenuItem();
  }

  @Override
  public void bind(IMenuBarView view) {
    view.getEditAttributesRangeMenuItem().setOnAction(event -> onEditAttributesRangeSelection());

    view.getEditEntriesFilePathMenuItem().setOnAction(event -> onEditEntriesPathSelection());
  }

  private void onEditAttributesRangeSelection() {
    OkCancelDialog dialog = new OkCancelDialog("Edit Attributes Range", StageStyle.UTILITY, Modality.APPLICATION_MODAL,
            true, true);
    EditAttributesRangeController editAttributesRangeController = new EditAttributesRangeController();
    EditAttributesRangeController.IEditAttributesRangeView iEditAttributesRangeView = new EditAttributesRangeView();
    dialog.setContent(iEditAttributesRangeView.asNode());
    dialog.getOkButton().setOnAction(event -> {

    });
    dialog.show();
  }

  private void onEditEntriesPathSelection() {
    OkCancelDialog dialog = new OkCancelDialog("Edit Entries File Path", StageStyle.UTILITY, Modality.APPLICATION_MODAL,
            true, true);
    EditEntriesPathController editEntriesPathController = new EditEntriesPathController(dialog.getStage());
    EditEntriesPathController.IEditEntriesPathView iEditEntriesPathView = new EditEntriesPathView();
    editEntriesPathController.bind(iEditEntriesPathView);
    dialog.setContent(iEditEntriesPathView.asNode());
    dialog.getOkButton().setOnAction(event -> {
      dialog.close();
      MainController.getInstance().setEntriesPath(editEntriesPathController.getSelectedPath());
    });
    dialog.show();
  }
}
