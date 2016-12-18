package sample.utils.enums;

public enum Distances {

  EUCLIDEAN("Euclidean distance"), SQUARED_EUCLIDEAN("Squared Euclidean Distance"), CITY_BLOCK("City Block Distance");

  String displayName;

  Distances(String displayName) {
    this.displayName = displayName;
  }

  @Override
  public String toString() {
    return displayName;
  }
}
