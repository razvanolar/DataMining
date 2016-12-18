package sample.utils;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class FillToolItem extends Region {

  public FillToolItem() {
    HBox.setHgrow(this, Priority.ALWAYS);
    this.setMinWidth(Region.USE_PREF_SIZE);
  }
}
