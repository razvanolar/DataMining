package sample.components.charts;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import sample.components.charts.clusters.ClustersController;
import sample.components.charts.clusters.ClustersView;
import sample.models.Cluster;
import sample.models.ClusterResultEntry;
import sample.utils.interfaces.Controller;
import sample.utils.interfaces.View;

import java.util.List;

public class ChartsController implements Controller<ChartsController.IChartsView> {

  public interface IChartsView extends View {
    TabPane getTabPane();
  }

  private IChartsView view;

  @Override
  public void bind(IChartsView view) {
    this.view = view;
  }

  public void addNewTab(String name, List<Cluster> clusters, List<ClusterResultEntry> resultEntries, boolean[] mask) {
    ClustersController clustersController = new ClustersController(getDimensions(mask), resultEntries, clusters);
    ClustersController.IClustersView iClustersView = new ClustersView();
    clustersController.bind(iClustersView);
    Tab tab = new Tab(name);
    tab.setContent(iClustersView.asNode());
    view.getTabPane().getTabs().add(tab);
  }

  private int getDimensions(boolean[] mask) {
    int dimensions = 0;
    for (boolean b : mask)
      if (b)
        dimensions ++;
    return dimensions;
  }
}
