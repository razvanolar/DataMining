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
  private MenuItem editEntriesFilePathMenuItem;

  public MenuBarView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    fileMenu = new Menu("File");
    editMenu = new Menu("Edit");
    helpMenu = new Menu("Help");
    editAttributesRangeMenuItem = new MenuItem("Attributes Range");
    editEntriesFilePathMenuItem = new MenuItem("Entries Path");

    editMenu.getItems().addAll(editAttributesRangeMenuItem, editEntriesFilePathMenuItem);

    menuBar = new MenuBar(fileMenu, editMenu, helpMenu);
  }

  public MenuItem getEditAttributesRangeMenuItem() {
    return editAttributesRangeMenuItem;
  }

  @Override
  public Node asNode() {
    return menuBar;
  }
}
