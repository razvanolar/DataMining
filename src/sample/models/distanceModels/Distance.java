package sample.models.distanceModels;

import sample.models.ClusterEntry;

/**
 * Created by Cristi on 12/19/2016.
 */
public interface Distance {
  float getDistance(ClusterEntry a, ClusterEntry b);
}
