package sample.components.data_set_view;

import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import sample.components.MainController;
import sample.models.Entry;
import sample.models.FormattedEntry;
import sample.models.RawEntry;
import sample.utils.interfaces.Controller;
import sample.utils.interfaces.View;

public class DataSetController implements Controller<DataSetController.IDataSetView> {

  public interface IDataSetView extends View {
    TableView<Entry> getEntryTableView();
  }

  private IDataSetView view;

  @Override
  public void bind(IDataSetView view) {
    this.view = view;
    view.getEntryTableView().getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
          System.out.println("selected");
          if (newValue == null)
            return;
          FormattedEntry formattedEntry = null;
          if (newValue instanceof RawEntry) {
            formattedEntry = Entry.fromRawToFormatted((RawEntry) newValue);
          } else {
            formattedEntry = (FormattedEntry) newValue;
          }
          MainController.getInstance().getMainView().getText().setText(MainController.getInstance().getRepo().getTree().findPath(formattedEntry));
        });
  }

  public void load(boolean isRawFormat) {
    view.getEntryTableView().getItems().clear();
    if (isRawFormat)
      view.getEntryTableView().getItems().addAll(MainController.getInstance().getRepo().getRawEntries());
    else
      view.getEntryTableView().getItems().addAll(MainController.getInstance().getRepo().getFormattedEntries());
  }
}
