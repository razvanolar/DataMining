package sample.utils.clustering;

import sample.models.Cluster;
import sample.models.ClusterEntry;
import sample.utils.repository.FileRepository;
import sample.utils.repository.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by razvanolar on 14.12.2016
 */
public class ClusteringAlg {

  public static void main(String[] args) {
    try {
      System.out.println("Validating arguments");
      ClusteringAlg clusteringAlg = new ClusteringAlg();
      if (!clusteringAlg.validateParameters(args)) {
        clusteringAlg.printHelpMessage();
        return;
      }
      System.out.println("Initialising repository");
      System.out.println(ClusteringAlg.class.getProtectionDomain().getCodeSource().getLocation().getPath());
      Repository repo = new FileRepository(args[1], ClusteringAlg.class.getProtectionDomain().getCodeSource().getLocation().getPath() + ".\\sample\\utils\\data\\attribute_range.txt");
      boolean[] mask = {true, true, true, true, true, true, true, true};
      System.out.println("Retrieve entries");
      List<ClusterEntry> clusterEntries = repo.getPartialRawEntries(mask);
      int clustersNumber = Integer.parseInt(args[0]);
      if (clusterEntries == null || clustersNumber > clusterEntries.size()) {
        System.out.println("Unable to compute clusters on so few entries.");
        clusteringAlg.printHelpMessage();
        return;
      }
      System.out.println("Run clustering...");
      List<Cluster> clusters = clusteringAlg.kmean(clusterEntries, clustersNumber, (float) 0.2);
      clusteringAlg.printClusters(clusters);
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
        float smallestDistance = ClusteringUtils.distance.getDistance(p, clusters.get(0).getCentroid());
        int clusterIndex = 0;
        for (int i = 0; i < clusterCount - 1; i ++) {
          float distance = ClusteringUtils.distance.getDistance(p, clusters.get(i + 1).getCentroid());
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

  private boolean validateParameters(String[] args) {
    if (args == null || args.length != 2) {
      System.out.println("Incorrect provided parameters.");
      return false;
    }
    try {
      if (Integer.parseInt(args[0]) < 2) {
        System.out.println("Incorrect provided clusters number.");
        return false;
      }
      File file = new File(args[1]);
      if (!file.exists()) {
        System.out.println("Specified entries file does not exists.");
        return false;
      }
      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return false;
  }

  private void printHelpMessage() {
    System.out.println("java ClusteringAlg clusters_number entries_path");
    System.out.println("\tclusters_number - number of clusters to be generated");
    System.out.println("\tentries_path - path to the file containing the entries on which to apply the clustering");
  }

  private void printClusters(List<Cluster> clusters) {
    for (Cluster c : clusters) {
      for (ClusterEntry p : c.getClusterEntries()) {
        System.out.println("Cluster: " + clusters.indexOf(c) + "\t RawEntry: " + p.getValues());
      }
    }
  }
}
