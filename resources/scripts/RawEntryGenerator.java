import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by razvanolar on 16.12.2016
 */
public class RawEntryGenerator {

  private void generate(int entryNumbers, String prefix, String sufix, File file, boolean showHeader) throws Exception {
    PrintWriter writer = null;
    try {
      System.out.println("Start generating entries...");
      Random rand = new Random();
      writer = new PrintWriter(file);
      if (showHeader)
        writer.println("age#height#weight#sex#activity_domain#effort_level#avg_speed#distance");
      for (int i= 0; i < entryNumbers; i ++) {
        String entry = prefix + toCSV(generateEntry(rand)) + sufix;
        System.out.write(("Progress " + (int)(((float)i / (float)entryNumbers) * 100) + "%\r").getBytes());
        writer.println(entry);
      }
      System.out.println("Progress 100%");
      System.out.println("Done!");
    } finally {
      if (writer != null)
        writer.close();
    }
  }

  private List<Number> generateEntry(Random rand) {
    int age = rand.nextInt(65) + 16; // 16 - 80
    float height = rand.nextFloat() * ((float)2.30 - (float)1.35) + (float)1.35; // 1.35 - 2.30
    int weight = rand.nextInt(146) + 35; // 35 - 180
    int sex = rand.nextInt(2);
    int activity_domain = rand.nextInt(6) + 1;
    int effort_level = rand.nextInt(5) + 1;
    float time_hours = rand.nextFloat() + (float) 0.1 + (effort_level / (float) 10) + rand.nextInt(effort_level);
    float average_speed = getAverageSpeed(rand, age, weight, height, effort_level);
    float distance = time_hours * average_speed;
    List<Number> coordinates = new ArrayList<>(8);
    coordinates.add(age);
    coordinates.add(height);
    coordinates.add(weight);
    coordinates.add(sex);
    coordinates.add(activity_domain);
    coordinates.add(effort_level);
    coordinates.add(average_speed);
    coordinates.add(distance);
    return coordinates;
  }

  private float getAverageSpeed(Random rand, float age, float weight, float height, float effort_level) {
    float speed;
    if (weight > 130)
      speed = rand.nextFloat();
    else if (weight > 90)
      speed = rand.nextFloat() + 2;
    else if (weight > 70 || weight < 40)
      speed = rand.nextFloat() + 4;
    else
      speed = rand.nextFloat() + 5;

    if (height < 1.60)
      speed += rand.nextFloat() + 0.75;
    else if (height < 1.80)
      speed += rand.nextFloat() + 1.15;
    else
      speed += rand.nextFloat() + 1.35;

    if (age < 18)
      speed += rand.nextFloat() + 0.75;
    else if (age < 35)
      speed += 1.75;
    else if (age < 50)
      speed += rand.nextFloat() + 0.35;
    else
      speed += rand.nextFloat();

    speed += effort_level + rand.nextInt((int) effort_level);

    return speed;
  }

  private String toCSV(List<Number> coordinates) {
    String result = "";
    for (int i = 0; i < coordinates.size(); i ++) {
      Number number = coordinates.get(i);
      result += number;
      if (i < coordinates.size() - 1)
        result += ",";
    }
    return result;
  }

  private static boolean validArguments(String[] args) {
    try {
      int entries = Integer.parseInt(args[0]);
      if (entries < 1) {
        System.out.println("Incorrect entries number");
        return false;
      }
      if (!args[1].equalsIgnoreCase("-d") && !args[1].equalsIgnoreCase("-f")) {
        System.out.println("Unknown format attribute");
        return false;
      }
      File file = new File(args[2]);
      if (!file.exists() && !file.createNewFile()) {
        System.out.println("Specified file does not exist");
        return false;
      }
      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return false;
  }

  private static void printHelpMessage() {
    System.out.println("Please provide the number of entries to be generated.");
    System.out.println("java RawEntryGenerator entries_number -[d|f] file_path");
    System.out.println("\tentries_number - the number of entries to be generated [1,n]");
    System.out.println("\t-[d|f]");
    System.out.println("\t\t-d - to generate database entries");
    System.out.println("\t\t-f - to generate text file entries");
    System.out.println("\tfile_path - path of the file in which the entries will be saved");
  }

  public static void main(String[] args) {
    try {
      if (args.length != 3 || !RawEntryGenerator.validArguments(args)) {
        RawEntryGenerator.printHelpMessage();
        return;
      }
      int entries = Integer.parseInt(args[0]);
      if (args[1].equalsIgnoreCase("-f"))
        new RawEntryGenerator().generate(entries, "", "", new File(args[2]), true);
      else
        new RawEntryGenerator().generate(entries,
                "INSERT INTO entry (age, height, weight, sex, activity_domain, effort_level, avg_speed, distance) VALUE " + "(",
                ");", new File(args[2]), false);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
