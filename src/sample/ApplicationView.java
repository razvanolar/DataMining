package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.components.MainController;
import sample.components.MainView;

import java.io.File;

public class ApplicationView extends Application {

  private Scene scene;
  private static final String PATH = "resources\\";
  private static final String DEFAULT_THEME = "default_theme.css";

  @Override
  public void init() throws Exception {
    scene = new Scene((StackPane)(new MainView()).asNode(), 800, 450);
    scene.getStylesheets().addAll(getDefaultTheme());
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Data Mining");
    primaryStage.setScene(scene);
    primaryStage.setMaximized(true);
    primaryStage.show();
    MainController.getInstance().load();
  }

  private static String getDefaultTheme() {
    return getFilePath(new File(PATH + DEFAULT_THEME));
  }

  private static String getFilePath(File file) {
    return file.exists() ? "file:///" + file.getAbsolutePath().replace("\\", "/") : null;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
