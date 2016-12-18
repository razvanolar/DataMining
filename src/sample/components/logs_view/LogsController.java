package sample.components.logs_view;

import sample.utils.interfaces.Controller;
import sample.utils.interfaces.View;

public class LogsController implements Controller<LogsController.ILogView> {

  public interface ILogView extends View {

  }

  @Override
  public void bind(ILogView view) {

  }
}
