package sample.components.charts.clusters;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.TableView;
import sample.models.Cluster;
import sample.models.ClusterEntry;
import sample.models.ClusterResultEntry;
import sample.models.Entry;
import sample.utils.interfaces.Controller;
import sample.utils.interfaces.View;

import java.util.ArrayList;
import java.util.List;

public class ClustersController implements Controller<ClustersController.IClustersView> {

  public interface IClustersView extends View {
    TableView<Entry> getTableView();
    void addChart(Node chart);
  }

  private IClustersView view;
  private List<Cluster> clusters;
  private List<ClusterResultEntry> clusterResultEntries;
  private boolean[] mask;
  private List<String> options;
  private ChartOptionsView chartOptionsView;

  public ClustersController(boolean[] mask, List<ClusterResultEntry> clusterResultEntries, List<Cluster> clusters) {
    this.mask = mask;
    this.clusterResultEntries = clusterResultEntries;
    this.clusters = clusters;
  }

  @Override
  public void bind(IClustersView view) {
    this.view = view;
    this.options = buildOptionsList(mask);
    ObservableList<Entry> items = view.getTableView().getItems();
    for (ClusterResultEntry entry : clusterResultEntries) {
      items.addAll(entry);
    }

    if (getDimensions(mask) >= 2) {
      createScatteredChart(0, 1);
    }
  }

  private ChartOptionsView createChartOptionsView(Chart chart) {
    ChartOptionsView chartView = new ChartOptionsView(chart, options);

    chartView.getApplyButton().setOnAction(event -> {
      if (chartView.getScatterRadioButton().isSelected()) {
        createScatteredChart(options.indexOf(chartView.getxComboBox().getValue()),
                options.indexOf(chartView.getyComboBox().getValue()));
      } else {
        createPieChart();
      }
    });

    return chartView;
  }

  private void createScatteredChart(int xIndex, int yIndex) {
    ScatterChart<Number, Number> chart = new ScatterChart<>(new NumberAxis(), new NumberAxis());
    for (int i = 0; i < clusters.size(); i ++) {
      Cluster c = clusters.get(i);
      XYChart.Series series = new XYChart.Series();
      series.setName("Cluster " + i);
      for (ClusterEntry clusterEntry : c.getClusterEntries()) {
        List<Float> coordinates = clusterEntry.getCoordinates();
        if (coordinates == null || coordinates.size() < 2)
          continue;
        series.getData().add(new XYChart.Data(coordinates.get(xIndex), coordinates.get(yIndex)));
      }
      chart.getData().add(series);
    }
    if (chartOptionsView == null) {
      chartOptionsView = createChartOptionsView(chart);
      view.addChart(chartOptionsView.asNode());
    } else {
      chartOptionsView.setChart(chart);
    }
  }

  private void createPieChart() {
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    for (int i = 0; i < clusters.size(); i ++) {
      pieChartData.addAll(new PieChart.Data("Cluster " + i, clusters.get(i).getClusterEntries().size()));
    }
    PieChart chart = new PieChart(pieChartData);

    if (chartOptionsView == null) {
      chartOptionsView = createChartOptionsView(chart);
      view.addChart(chartOptionsView.asNode());
    } else {
      chartOptionsView.setChart(chart);
    }
  }

  private int getDimensions(boolean[] mask) {
    int dimensions = 0;
    for (boolean b : mask)
      if (b)
        dimensions ++;
    return dimensions;
  }

  private List<String> buildOptionsList(boolean[] mask) {
    //TODO Use a more generic way
    List<String> result = new ArrayList<>(8);
    if (mask[0])
      result.add("Age");
    if (mask[1])
      result.add("Height");
    if (mask[2])
      result.add("Weight");
    if (mask[3])
      result.add("Sex");
    if (mask[4])
      result.add("Activity");
    if (mask[5])
      result.add("Effort Level");
    if (mask[6])
      result.add("Average Speed");
    if (mask[7])
      result.add("Distance");
    return result;
  }
}
