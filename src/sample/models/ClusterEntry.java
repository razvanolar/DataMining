package sample.models;

import java.util.ArrayList;
import java.util.List;

public class ClusterEntry {

  private List<Float> coordinates;

  public ClusterEntry(String line) {
    String[] split = line.split(",");
    coordinates = new ArrayList<>(split.length);
    for (String s : split)
      coordinates.add(Float.parseFloat(s));
  }

  public ClusterEntry(List<Float> coordinates) {
    this.coordinates = coordinates;
  }

  public List<Float> getCoordinates() {
    return coordinates;
  }

  public int getDimensions() {
    return coordinates.size();
  }
}
