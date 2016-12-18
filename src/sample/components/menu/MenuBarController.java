package sample.components.menu;

import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import sample.components.edit_attributes_range.EditAttributesRangeController;
import sample.components.edit_attributes_range.EditAttributesRangeView;
import sample.utils.interfaces.Controller;
import sample.utils.interfaces.View;
import sample.utils.views.OkCancelDialog;

public class MenuBarController implements Controller<MenuBarController.IMenuBarView> {

  public interface IMenuBarView extends View {
    MenuItem getEditAttributesRangeMenuItem();
  }

  @Override
  public void bind(IMenuBarView view) {
    view.getEditAttributesRangeMenuItem().setOnAction(event -> {
      onEditAttributesRangeSelection();
    });
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
}
