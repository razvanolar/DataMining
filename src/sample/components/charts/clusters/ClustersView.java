package sample.components.charts.clusters;

import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import sample.models.Entry;
import sample.utils.views.ViewsUtil;

public class ClustersView implements ClustersController.IClustersView {

  private SplitPane splitPane;
  private TableView<Entry> tableView;

  public ClustersView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    tableView = ViewsUtil.createEntriesTable(true);
    splitPane = new SplitPane(tableView);
  }

  public TableView<Entry> getTableView() {
    return tableView;
  }

  @Override
  public Node asNode() {
    return splitPane;
  }
}
