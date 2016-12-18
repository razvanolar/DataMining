package sample.components.menu;

import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuBarView implements MenuBarController.IMenuBarView {

  private MenuBar menuBar;
  private Menu fileMenu;
  private Menu editMenu;
  private Menu helpMenu;
  private MenuItem editAttributesRangeMenuItem;

  public MenuBarView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    fileMenu = new Menu("File");
    editMenu = new Menu("Edit");
    helpMenu = new Menu("Help");

    editAttributesRangeMenuItem = new MenuItem("Attributes Range");

    editMenu.getItems().add(editAttributesRangeMenuItem);

    menuBar = new MenuBar(fileMenu, editMenu, helpMenu);
  }

  @Override
  public Node asNode() {
    return menuBar;
  }
}
