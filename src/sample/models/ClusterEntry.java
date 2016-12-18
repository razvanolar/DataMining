package sample.models;

import java.util.ArrayList;
import java.util.List;

public class ClusterEntry {

  private List<Float> coordinates;
  private List<String> values;

  public ClusterEntry(String line) {
    String[] split = line.split(",");
    coordinates = new ArrayList<>(split.length);
    values = new ArrayList<>(split.length);
    for (String s : split) {
      coordinates.add(Float.parseFloat(s));
      values.add(s);
    }
  }

  public ClusterEntry(List<Float> coordinates) {
    this.coordinates = coordinates;
  }

  public ClusterEntry(List<Float> coordinates, List<String> values) {
    this.coordinates = coordinates;
    this.values = values;
  }

  public List<Float> getCoordinates() {
    return coordinates;
  }

  public List<String> getValues() {
    return values;
  }

  public int getDimensions() {
    return coordinates.size();
  }
}
