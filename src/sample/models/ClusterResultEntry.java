package sample.models;

import java.util.List;

public class ClusterResultEntry extends Entry {

  public int clusterId;

  public ClusterResultEntry(int clusterId, List<String> values) {
    this.clusterId = clusterId;
    this.values = values;
  }

  @Override
  public String getByIndex(int index) {
    if (index == 0)
      return clusterId + "";
    return super.getByIndex(index-1);
  }
}
