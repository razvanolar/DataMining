package sample.components.edit_entries_path;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class EditEntriesPathView implements EditEntriesPathController.IEditEntriesPathView {

  private GridPane gridPane;
  private TextField pathTextField;
  private Button pathButton;
  private CheckBox useDefaultCheckBox;

  public EditEntriesPathView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    gridPane = new GridPane();
    pathTextField = new TextField();
    pathButton = new Button("...");
    useDefaultCheckBox = new CheckBox();

    gridPane.add(new Label("Entries Path: "), 0 ,0);
    gridPane.add(pathTextField, 1, 0);
    gridPane.add(pathButton, 2, 0);
    gridPane.add(new Label("Use Default: "), 0, 1);
    gridPane.add(useDefaultCheckBox, 1, 1);

    gridPane.setPadding(new Insets(15));
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setHgap(5);
    gridPane.setVgap(5);

    pathTextField.setMinWidth(350);
  }

  public TextField getPathTextField() {
    return pathTextField;
  }

  public Button getPathButton() {
    return pathButton;
  }

  public CheckBox getUseDefaultCheckBox() {
    return useDefaultCheckBox;
  }

  @Override
  public Node asNode() {
    return gridPane;
  }
}
