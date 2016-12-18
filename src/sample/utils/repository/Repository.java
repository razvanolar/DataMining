package sample.utils.repository;

import sample.models.*;
import sample.utils.id3.DecisionTree;
import sample.utils.id3.ID3Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Repository {
  private List<FormattedEntry> formattedEntries;
  private List<RawEntry> rawEntries;
  private List<ClusterEntry> clusterEntries;
  private DecisionTree tree;
  private String rules;

  protected void loadData() {
    ID3Utils.namesMap = getNamesMap();
    ID3Utils.ATTR_NUMBER = ID3Utils.namesMap.size() - 1;
    ID3Utils.attributeRangeMap = getAttributeRange();
    List<String> strings = getAllValuesAsStringList();
    formattedEntries = new ArrayList<>();
    rawEntries = new ArrayList<>();
    clusterEntries = new ArrayList<>();
    for (String string : strings) {
      formattedEntries.add(new FormattedEntry(string));
      rawEntries.add(new RawEntry(string));
      clusterEntries.add(new ClusterEntry(string));
    }
    tree = new DecisionTree(formattedEntries);
    tree.run();
  }

  public void reloadData() {
    loadData();
  }

  public abstract List<String> getAllValuesAsStringList();

  public abstract Map<Long, String> getNamesMap();

  public abstract Map<Long, List<AttributeRange>> getAttributeRange();

  public List<ClusterEntry> getPartialRawEntries(boolean[] attributes) {
    List<ClusterEntry> result = new ArrayList<>();
    for (ClusterEntry entry : clusterEntries) {
      List<Float> coordinates = new ArrayList<>();
      for (int i = 0; i < attributes.length; i++) {
        if (attributes[i]) {
          coordinates.add(entry.getCoordinates().get(i));
        }
      }
      result.add(new ClusterEntry(coordinates, new ArrayList<String>(entry.getValues())));
    }
    return result;
  }

  public List<ClusterResultEntry> getClusterResultEntries(List<Cluster> clusters) {
    if (clusters == null || clusters.isEmpty())
      return new ArrayList<>();
    List<ClusterResultEntry> result = new ArrayList<>();
    for (int i = 0; i < clusters.size(); i ++)
      result.addAll(convertClusterEntries(clusters.get(i).getClusterEntries(), i));
    return result;
  }

  private List<ClusterResultEntry> convertClusterEntries(List<ClusterEntry> clusterEntries, int clusterIndex) {
    if (clusterEntries == null || clusterEntries.isEmpty())
      return new ArrayList<>();
    List<ClusterResultEntry> result = new ArrayList<>(clusterEntries.size());
    for (ClusterEntry entry : clusterEntries)
      result.add(new ClusterResultEntry(clusterIndex, entry.getValues()));
    return result;
  }

  public List<FormattedEntry> getFormattedEntries() {
    return formattedEntries;
  }

  public List<RawEntry> getRawEntries() {
    return rawEntries;
  }

  public DecisionTree getTree() {
    return tree;
  }

  public void setTree(DecisionTree tree) {
    this.tree = tree;
  }
}
