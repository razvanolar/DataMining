package sample.components;

import sample.components.charts.ChartsController;
import sample.components.data_set_view.DataSetController;
import sample.components.toolbar.ToolbarController;
import sample.models.Cluster;
import sample.utils.clustering.ClusteringAlg;
import sample.utils.enums.Distances;
import sample.utils.enums.MainContentTypes;
import sample.utils.enums.RepositoryTypes;
import sample.utils.repository.FileRepository;
import sample.utils.repository.JDBCRepository;
import sample.utils.repository.Repository;

import java.util.List;

public class MainController {

  private static MainController INSTANCE;
  public static String ENTRIES_PATH = "C:\\Users\\razvanolar\\Desktop\\DataMiningApp\\src\\sample\\utils\\data\\entries.txt";
  public static String ATTRIBUTES_RANGE_PATH = "C:\\Users\\razvanolar\\Desktop\\DataMiningApp\\src\\sample\\utils\\data\\attribute_range.txt";

  private MainView mainView;
  private DataSetController dataSetController;
  private ToolbarController toolbarController;
  private ChartsController chartsController;
  private Repository repo;

  public void inject(MainView mainView, DataSetController dataSetController, ToolbarController toolbarController, ChartsController chartsController) {
    this.mainView = mainView;
    this.dataSetController = dataSetController;
    this.toolbarController = toolbarController;
    this.chartsController = chartsController;
  }

  public void setMainContentType(MainContentTypes contentType) {
    mainView.setCenterContent(contentType);
  }

  public void load() {
    load(RepositoryTypes.DATABASE);
  }

  public void load(RepositoryTypes repositoryTypes) {
    mainView.mask("Loading...");
    try {
      repo = repositoryTypes == RepositoryTypes.DATABASE ? new JDBCRepository() : new FileRepository(ENTRIES_PATH, ATTRIBUTES_RANGE_PATH);
      dataSetController.load();
    } catch (Exception e) {
      e.printStackTrace();
    }
    mainView.unmask();
  }

  public void applyClustering(String tabName, int clustersNumber, Distances distanceFormula, boolean[] mask) {
    try {
      ClusteringAlg clusteringAlg = new ClusteringAlg();
      List<Cluster> clusters = clusteringAlg.kmean(repo.getPartialRawEntries(mask), clustersNumber, (float) 0.3);
      toolbarController.chageToChartView();
      chartsController.addNewTab(tabName, repo.getClusterResultEntries(clusters), mask);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Repository getRepo() {
    return repo;
  }

  public static MainController getInstance() {
    if (INSTANCE == null)
      INSTANCE = new MainController();
    return INSTANCE;
  }
}
