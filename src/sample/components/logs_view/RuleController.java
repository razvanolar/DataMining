package sample.components.logs_view;

import javafx.scene.text.Text;
import sample.components.MainController;
import sample.utils.interfaces.Controller;
import sample.utils.interfaces.View;

public class RuleController implements Controller<RuleController.IRuleView> {

  private IRuleView view;

  public interface IRuleView extends View {
    Text getText();
  }

  @Override
  public void bind(IRuleView view) {
    this.view = view;
  }

  public void load() {
    view.getText().setText(MainController.getInstance().getRepo().getTree().getPrintPathList());
  }
}
