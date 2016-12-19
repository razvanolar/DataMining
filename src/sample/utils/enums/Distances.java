package sample.utils.enums;

import sample.models.distanceModels.CityBlockDistance;
import sample.models.distanceModels.Distance;
import sample.models.distanceModels.EuclidianDistance;
import sample.models.distanceModels.SquareEuclidianDistance;

public enum Distances {

  EUCLIDEAN("Euclidean distance", new EuclidianDistance()),
  SQUARED_EUCLIDEAN("Squared Euclidean Distance", new SquareEuclidianDistance()),
  CITY_BLOCK("City Block Distance", new CityBlockDistance());

  String displayName;
  Distance distance;

  Distances(String displayName, Distance distance) {
    this.displayName = displayName;
    this.distance = distance;
  }

  @Override
  public String toString() {
    return displayName;
  }

  public Distance getDistance() {
    return distance;
  }
}
