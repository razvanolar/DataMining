package sample.utils.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Activities {
  PROGRAMMMER(1), LAWYER(2), DOCTOR(3), POLICEMAN(4), ENGENEER(5), OTHER(6);

  int id;

  Activities(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public static List<Activities> listValues() {
    return Arrays.stream(values()).collect(Collectors.toList());
  }
}
