package sample.components;

import sample.components.data_set_view.DataSetController;
import sample.utils.enums.MainContentTypes;
import sample.utils.enums.RepositoryTypes;
import sample.utils.repository.FileRepository;
import sample.utils.repository.JDBCRepository;
import sample.utils.repository.Repository;

public class MainController {

  private static MainController INSTANCE;
  public static String ENTRIES_PATH = "C:\\Users\\razvanolar\\Desktop\\DataMiningApp\\src\\sample\\utils\\data\\entries.txt";
  public static String ATTRIBUTES_RANGE_PATH = "C:\\Users\\razvanolar\\Desktop\\DataMiningApp\\src\\sample\\utils\\data\\attribute_range.txt";

  private MainView mainView;
  private DataSetController dataSetController;
  private Repository repo;

  public void inject(MainView mainView, DataSetController dataSetController) {
    this.mainView = mainView;
    this.dataSetController = dataSetController;
  }

  public void setMainContentType(MainContentTypes contentType) {
    mainView.setCenterContent(contentType);
  }

  public void load() {
    load(RepositoryTypes.DATABASE);
  }

  public void load(RepositoryTypes repositoryTypes) {
    try {
      repo = repositoryTypes == RepositoryTypes.DATABASE ? new JDBCRepository() : new FileRepository(ENTRIES_PATH, ATTRIBUTES_RANGE_PATH);
      dataSetController.load();
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
