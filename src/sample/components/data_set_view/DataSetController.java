package sample.components.data_set_view;

import javafx.scene.control.TableView;
import sample.components.MainController;
import sample.models.Entry;
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
  }

  public void load() {
    view.getEntryTableView().getItems().clear();
    view.getEntryTableView().getItems().addAll(MainController.getInstance().getRepo().getRawEntries());
  }
}
