package sample.components.data_set_view;

import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import sample.models.Entry;
import sample.utils.views.ViewsUtil;

public class DataSetView implements DataSetController.IDataSetView {
  private BorderPane mainContainer;

  private TableView<Entry> entryTableView;

  public DataSetView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    entryTableView = ViewsUtil.createEntriesTable(false);
    mainContainer = new BorderPane(entryTableView);
  }

  public TableView<Entry> getEntryTableView() {
    return entryTableView;
  }

  @Override
  public Node asNode() {
    return mainContainer;
  }
}
