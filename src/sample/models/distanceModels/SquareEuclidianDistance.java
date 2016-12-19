package sample.models.distanceModels;

import sample.models.ClusterEntry;

import java.util.List;

/**
 * Created by Cristi on 12/19/2016.
 */
public class SquareEuclidianDistance implements Distance {
  @Override
  public float getDistance(ClusterEntry a, ClusterEntry b) {
    float total = 0;
    float diff;
    List<Float> aCoordinates = a.getCoordinates();
    List<Float> bCoordinates = b.getCoordinates();
    for (int i = 0; i < aCoordinates.size(); i++) {
      diff = bCoordinates.get(i) - aCoordinates.get(i);
      total += diff * diff;
    }
    return total;
  }
}
