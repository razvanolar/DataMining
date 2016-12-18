package sample.components.logs_view;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RuleView implements RuleController.IRuleView {

  private ScrollPane mainContainer;
  private Text text;

  public RuleView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    text = new Text("");
    mainContainer = new ScrollPane();
    mainContainer.setContent(new VBox(text));
  }

  @Override
  public Node asNode() {
    return mainContainer;
  }

  @Override
  public Text getText() {
    return text;
  }
}
