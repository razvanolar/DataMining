package sample.components.data_set_view;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import sample.models.Entry;

public class DataSetView implements DataSetController.IDataSetView {
  private BorderPane mainContainer;

  private TableView<Entry> entryTableView;

  public DataSetView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    entryTableView = new TableView<>();
    mainContainer = new BorderPane(entryTableView);

    TableColumn<Entry, String> ageColumn = createColumn("Age", 0, 0.124);
    TableColumn<Entry, String> heightColumn = createColumn("Height", 1, 0.125);
    TableColumn<Entry, String> weightColumn = createColumn("Weight", 2, 0.125);
    TableColumn<Entry, String> sexColumn = createColumn("Sex", 3, 0.124);
    TableColumn<Entry, String> activityColumn = createColumn("Activity", 4, 0.125);
    TableColumn<Entry, String> effortColumn = createColumn("Effort Level", 5, 0.125);
    TableColumn<Entry, String> speedColumn = createColumn("Average Speed", 6, 0.125);
    TableColumn<Entry, String> distanceColumn = createColumn("Distance", 7, 0.125);

    entryTableView.getColumns().addAll(ageColumn, heightColumn, weightColumn, sexColumn, activityColumn, effortColumn,
            speedColumn, distanceColumn);
  }

  private TableColumn<Entry, String> createColumn(String name, int index, double multiplier) {
    TableColumn<Entry, String> column = new TableColumn<>(name);
    column.setCellValueFactory(param -> {
      if (param != null && param.getValue() != null)
        return new ReadOnlyStringWrapper(param.getValue().getByIndex(index));
      return null;
    });
    column.prefWidthProperty().bind(entryTableView.widthProperty().multiply(multiplier));
    return column;
  }

  public TableView<Entry> getEntryTableView() {
    return entryTableView;
  }

  @Override
  public Node asNode() {
    return mainContainer;
  }
}
