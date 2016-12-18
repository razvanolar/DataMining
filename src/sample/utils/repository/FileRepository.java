package sample.utils.repository;

import sample.models.AttributeRange;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileRepository extends Repository {
    String entriesPath;
    String attributePath;

    public FileRepository(String entriesPath, String attributePath) {
        this.entriesPath = entriesPath;
        this.attributePath = attributePath;
        loadData();
    }

    @Override
    public List<String> getAllValuesAsStringList() {
        BufferedReader reader = null;
        List<String> result = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(new File(entriesPath)));
            String line = reader.readLine(); //skip first line since it is with the names
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    result.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return result;
    }

    @Override
    public Map<Long, String> getNamesMap() {
        BufferedReader reader = null;
        Map<Long, String> result = new HashMap<>();
        try {
            reader = new BufferedReader(new FileReader(new File(entriesPath)));
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                String[] array = line.split("#");
                for (int i=0; i<array.length; i++) {
                    result.put((long) i, array[i]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return result;
    }

    @Override
    public Map<Long, List<AttributeRange>> getAttributeRange() {
        BufferedReader reader = null;
        Map<Long, List<AttributeRange>> result = new HashMap<>();
        try {
            reader = new BufferedReader(new FileReader(new File(attributePath)));
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] array = line.split(",");
                    Long id = Long.parseLong(array[0]);
                    String displayName = array[1];
                    Float min = null, max = null;
                    if (!array[2].equals("-") && !array[2].isEmpty()) {
                        min = Float.parseFloat(array[2]);
                    }
                    if (!array[3].equals("-") && !array[3].isEmpty()) {
                        max = Float.parseFloat(array[3]);
                    }
                    if (result.get(id) == null) {
                        result.put(id, new ArrayList<>());
                    }
                    result.get(id).add(new AttributeRange(displayName, min, max));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return result;
    }
}
