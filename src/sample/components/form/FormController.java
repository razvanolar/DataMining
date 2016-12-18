package sample.components.form;

import sample.utils.interfaces.Controller;
import sample.utils.interfaces.View;

public class FormController implements Controller<FormController.IFormView> {

  public interface IFormView extends View {

  }

  @Override
  public void bind(IFormView view) {

  }
}
