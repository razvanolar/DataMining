package sample.components.charts.clusters;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sample.models.Cluster;
import sample.models.ClusterResultEntry;
import sample.models.Entry;
import sample.utils.interfaces.Controller;
import sample.utils.interfaces.View;

import java.util.List;

public class ClustersController implements Controller<ClustersController.IClustersView> {

  public interface IClustersView extends View {
    TableView<Entry> getTableView();
  }

  private int dimensions;
  private List<Cluster> clusters;
  private List<ClusterResultEntry> clusterResultEntries;

  public ClustersController(int dimensions, List<ClusterResultEntry> clusterResultEntries) {
    this.dimensions = dimensions;
    this.clusterResultEntries = clusterResultEntries;
  }

  @Override
  public void bind(IClustersView view) {
    ObservableList<Entry> items = view.getTableView().getItems();
    for (ClusterResultEntry entry : clusterResultEntries) {
      items.addAll(entry);
    }
  }
}
