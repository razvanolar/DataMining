package sample.components;

import sample.utils.enums.MainContentTypes;

public class MainController {

  private static MainController INSTANCE;

  private MainView mainView;

  public void setMainView(MainView mainView) {
    this.mainView = mainView;
  }

  public void setMainContentType(MainContentTypes contentType) {
    mainView.setCenterContent(contentType);
  }

  public static MainController getInstance() {
    if (INSTANCE == null)
      INSTANCE = new MainController();
    return INSTANCE;
  }
}
