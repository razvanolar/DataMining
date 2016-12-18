package sample.models;

import sample.utils.clustering.ClusteringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by razvanolar on 14.12.2016
 */
public class Cluster {

  private List<ClusterEntry> rawEntries;
  private ClusterEntry centroid;

  public Cluster(List<ClusterEntry> rawEntries) {
    this.rawEntries = rawEntries;
    this.centroid = calculateCentroid();
  }

  public float update(List<ClusterEntry> rawEntries) {
    this.rawEntries = rawEntries;
    ClusterEntry oldCentroid = centroid;
    this.centroid = calculateCentroid();
    return ClusteringUtils.getDistance(oldCentroid, this.centroid);
  }

  private ClusterEntry calculateCentroid() {
    float[] v = new float[rawEntries.get(0).getDimensions()];
    for (ClusterEntry p : rawEntries) {
      List<Float> coordinates = p.getCoordinates();
      for (int i = 0; i < coordinates.size(); i ++) {
        v[i] += coordinates.get(i);
      }
    }

    List<Float> centr = new ArrayList<>(v.length);
    int size = rawEntries.size();
    for (float val : v) {
      centr.add(val / size);
    }

    return new ClusterEntry(centr);
  }

  public List<ClusterEntry> getClusterEntries() {
    return rawEntries;
  }

  public ClusterEntry getCentroid() {
    return centroid;
  }
}
