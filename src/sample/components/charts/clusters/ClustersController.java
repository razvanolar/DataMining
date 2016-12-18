package sample.components.charts.clusters;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableView;
import sample.models.Cluster;
import sample.models.ClusterEntry;
import sample.models.ClusterResultEntry;
import sample.models.Entry;
import sample.utils.interfaces.Controller;
import sample.utils.interfaces.View;

import java.util.List;

public class ClustersController implements Controller<ClustersController.IClustersView> {

  public interface IClustersView extends View {
    TableView<Entry> getTableView();
    void addChart(Node chart);
  }

  private IClustersView view;
  private int dimensions;
  private List<Cluster> clusters;
  private List<ClusterResultEntry> clusterResultEntries;

  public ClustersController(int dimensions, List<ClusterResultEntry> clusterResultEntries, List<Cluster> clusters) {
    this.dimensions = dimensions;
    this.clusterResultEntries = clusterResultEntries;
    this.clusters = clusters;
  }

  @Override
  public void bind(IClustersView view) {
    this.view = view;
    ObservableList<Entry> items = view.getTableView().getItems();
    for (ClusterResultEntry entry : clusterResultEntries) {
      items.addAll(entry);
    }

    if (dimensions == 2) {
      createChart();
    }
  }

  private void createChart() {
    ScatterChart<Number, Number> chart = new ScatterChart<>(new NumberAxis(), new NumberAxis());
    for (int i = 0; i < clusters.size(); i ++) {
      Cluster c = clusters.get(i);
      XYChart.Series series = new XYChart.Series();
      series.setName("Cluster " + i);
      for (ClusterEntry clusterEntry : c.getClusterEntries()) {
        List<Float> coordinates = clusterEntry.getCoordinates();
        if (coordinates == null || coordinates.size() != 2)
          continue;
        series.getData().add(new XYChart.Data(coordinates.get(0), coordinates.get(1)));
      }
      chart.getData().add(series);
    }
    view.addChart(chart);
  }
}
