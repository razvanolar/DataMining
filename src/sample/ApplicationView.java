package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.components.MainController;
import sample.components.MainView;

public class ApplicationView extends Application {

  private Scene scene;

  @Override
  public void init() throws Exception {
    scene = new Scene((StackPane)(new MainView()).asNode(), 800, 450);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Data Mining");
    primaryStage.setScene(scene);
    primaryStage.setMaximized(true);
    primaryStage.show();
    MainController.getInstance().load();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
