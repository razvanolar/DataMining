package sample.utils.clustering;

import sample.models.Cluster;
import sample.models.ClusterEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by razvanolar on 14.12.2016
 */
public class ClusteringAlg {

  public static void main(String[] args) {
    try {
      int clusterNumber = 500;
      float optimization = (float) 0.2;

      ClusteringAlg clusteringAlg = new ClusteringAlg();

//      List<Cluster> clusters = clusteringAlg.kmean(rawEntries, clusterNumber, optimization);
//      clusteringAlg.printClusters(clusters);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List<Cluster> kmean(List<ClusterEntry> rawEntries, int clusterNumbers, float optimization) {
    List<ClusterEntry> initial = ClusteringUtils.getRandomPointsList(rawEntries, clusterNumbers);

    List<Cluster> clusters = new ArrayList<>();
    for (ClusterEntry p : initial) {
      List<ClusterEntry> list = new ArrayList<>(1);
      list.add(p);
      clusters.add(new Cluster(list));
    }

    int loopCounter = 0;
    while (true) {
      List<List<ClusterEntry>> lists = new ArrayList<>(clusters.size());
      for (int i = 0; i < clusters.size(); i ++) {
        lists.add(new ArrayList<>());
      }

      int clusterCount = clusters.size();
      loopCounter ++;

      for (ClusterEntry p : rawEntries) {
        float smallestDistance = ClusteringUtils.getDistance(p, clusters.get(0).getCentroid());
        int clusterIndex = 0;
        for (int i = 0; i < clusterCount - 1; i ++) {
          float distance = ClusteringUtils.getDistance(p, clusters.get(i + 1).getCentroid());
          if (distance < smallestDistance) {
            smallestDistance = distance;
            clusterIndex = i + 1;
          }
        }
        lists.get(clusterIndex).add(p);
      }

      float biggestShift = (float) 0.0;
      for (int i = 0; i < clusterCount; i ++) {
        float shift = clusters.get(i).update(lists.get(i));
        biggestShift = Math.max(biggestShift, shift);
      }

      if (biggestShift < optimization) {
        System.out.println("Converged after " + loopCounter + " iterations");
        break;
      }
    }

    return clusters;
  }

  public void printClusters(List<Cluster> clusters) {
    for (Cluster c : clusters) {
      for (ClusterEntry p : c.getClusterEntries()) {
        System.out.println("Cluster: " + clusters.indexOf(c) + "\t RawEntry: " + p);
      }
    }
  }
}
