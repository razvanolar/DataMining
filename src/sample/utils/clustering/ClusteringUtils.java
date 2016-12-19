package sample.utils.clustering;

import sample.models.ClusterEntry;
import sample.models.distanceModels.Distance;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by razvanolar on 14.12.2016
 */
public class ClusteringUtils {

  private static Random rand = new Random();
  public static Distance distance;

  public static List<ClusterEntry> getRandomPointsList(List<ClusterEntry> rawEntries, int number) {
    List<ClusterEntry> newRawEntries = new ArrayList<>(rawEntries);
    List<ClusterEntry> result = new ArrayList<>(number);
    for (int i = 0; i < number; i ++) {
      result.add(newRawEntries.remove(rand.nextInt(newRawEntries.size())));
    }
    return result;
  }
}
