package sample.utils.views;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.models.Entry;

public class ViewsUtil {

  public static TableView<Entry> createEntriesTable(boolean showClusterIndex) {
    TableView tableView = new TableView<>();
    int i = 0;
    double firstColSize = 0.124;
    if (showClusterIndex) {
      tableView.getColumns().add(createColumn("Cluster ID", i++, 0.06, tableView));
      firstColSize = 0.064;
    }
    TableColumn<Entry, String> ageColumn = createColumn("Age", i++, firstColSize, tableView);
    TableColumn<Entry, String> heightColumn = createColumn("Height", i++, 0.125, tableView);
    TableColumn<Entry, String> weightColumn = createColumn("Weight", i++, 0.125, tableView);
    TableColumn<Entry, String> sexColumn = createColumn("Sex", i++, 0.124, tableView);
    TableColumn<Entry, String> activityColumn = createColumn("Activity", i++, 0.125, tableView);
    TableColumn<Entry, String> effortColumn = createColumn("Effort Level", i++, 0.125, tableView);
    TableColumn<Entry, String> speedColumn = createColumn("Average Speed", i++, 0.125, tableView);
    TableColumn<Entry, String> distanceColumn = createColumn("Distance", i, 0.125, tableView);
    tableView.getColumns().addAll(ageColumn, heightColumn, weightColumn, sexColumn, activityColumn, effortColumn,
            speedColumn, distanceColumn);
    return tableView;
  }

  private static TableColumn<Entry, String> createColumn(String name, int index, double multiplier, TableView<Entry> tableView) {
    TableColumn<Entry, String> column = new TableColumn<>(name);
    column.setCellValueFactory(param -> {
      if (param != null && param.getValue() != null)
        return new ReadOnlyStringWrapper(param.getValue().getByIndex(index));
      return null;
    });
    column.prefWidthProperty().bind(tableView.widthProperty().multiply(multiplier));
    return column;
  }
}
