package sample.utils.clustering;

import sample.models.ClusterEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by razvanolar on 14.12.2016
 */
public class ClusteringUtils {

  private static Random rand = new Random();

  public static float getDistance(ClusterEntry a, ClusterEntry b) {
    float total = 0;
    float diff;
    List<Float> aCoordinates = a.getCoordinates();
    List<Float> bCoordinates = b.getCoordinates();
    for (int i = 0; i < aCoordinates.size(); i ++) {
      diff = bCoordinates.get(i) - aCoordinates.get(i);
      total += diff * diff;
    }
    return (float) Math.sqrt(total);
  }

  public static List<ClusterEntry> getRandomPointsList(List<ClusterEntry> rawEntries, int number) {
    List<ClusterEntry> newRawEntries = new ArrayList<>(rawEntries);
    List<ClusterEntry> result = new ArrayList<>(number);
    for (int i = 0; i < number; i ++) {
      result.add(newRawEntries.remove(rand.nextInt(newRawEntries.size())));
    }
    return result;
  }
}
