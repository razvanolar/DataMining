package sample.utils.interfaces;

public interface Controller<T extends View> {
  void bind(T view);
//  View getView();
}
