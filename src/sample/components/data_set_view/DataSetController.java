package sample.components.data_set_view;

import javafx.scene.control.TableView;
import sample.models.Entry;
import sample.models.FormattedEntry;
import sample.utils.id3.ID3Utils;
import sample.utils.interfaces.Controller;
import sample.utils.interfaces.View;
import sample.utils.repository.FileUtil;
import sample.utils.repository.JDBCDao;
import sample.utils.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class DataSetController implements Controller<DataSetController.IDataSetView> {

  public interface IDataSetView extends View {
    TableView<Entry> getEntryTableView();
  }

  @Override
  public void bind(IDataSetView view) {
    view.getEntryTableView().getItems().addAll(generateEntries());
  }

  private List<Entry> generateEntries() {
    try {
      Repository repo = new FileUtil("D:\\Java Workspace\\DataMiningApp\\src\\sample\\utils\\data\\entries.txt", "D:\\Java Workspace\\DataMiningApp\\src\\sample\\utils\\data\\attribute_range.txt");
      ID3Utils.namesMap = repo.getNamesMap();
      ID3Utils.ATTR_NUMBER = ID3Utils.namesMap.size() - 1;
      ID3Utils.attributeRangeMap = repo.getAttributeRange();
      List<String> strings = repo.getAllValuesAsStringList();
      List<Entry> entries = new ArrayList<>();
      for (String string : strings) {
        entries.add(new FormattedEntry(string));
//        entries.add(new RawEntry(string));
      }
      return entries;
    } catch (Exception e) {
      return new ArrayList<>();
    }
  }
}
