package sample.components.edit_entries_path;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import sample.components.MainController;
import sample.utils.interfaces.Controller;
import sample.utils.interfaces.View;

import java.io.File;

public class EditEntriesPathController implements Controller<EditEntriesPathController.IEditEntriesPathView> {

  public interface IEditEntriesPathView extends View {
    TextField getPathTextField();
    Button getPathButton();
    CheckBox getUseDefaultCheckBox();
  }

  private Window parent;
  private IEditEntriesPathView view;

  public EditEntriesPathController(Window parent) {
    this.parent = parent;
  }

  @Override
  public void bind(IEditEntriesPathView view) {
    this.view = view;
    view.getPathTextField().setDisable(view.getUseDefaultCheckBox().isSelected());
    view.getPathButton().setDefaultButton(view.getUseDefaultCheckBox().isSelected());

    view.getPathButton().setOnAction(event -> {
      FileChooser chooser = new FileChooser();
      File file = chooser.showOpenDialog(parent);
      if (file != null) {
        view.getPathTextField().setText(file.getAbsolutePath());
      }
    });

    view.getUseDefaultCheckBox().selectedProperty().addListener((observable, oldValue, newValue) -> {
      view.getPathTextField().setDisable(newValue);
      view.getPathButton().setDisable(newValue);
      if (newValue)
        view.getPathTextField().setText("");
    });

    if (MainController.getInstance().getEntriesPath() != null) {
      view.getPathTextField().setText(MainController.getInstance().getEntriesPath());
      view.getUseDefaultCheckBox().setSelected(false);
    } else {
      view.getUseDefaultCheckBox().setSelected(true);
    }
  }

  public String getSelectedPath() {
    if (view.getPathTextField().getText() == null || view.getUseDefaultCheckBox().isSelected())
      return null;
    File file = new File(view.getPathTextField().getText());
    if (!file.exists())
      return null;
    return file.getAbsolutePath();
  }
}
