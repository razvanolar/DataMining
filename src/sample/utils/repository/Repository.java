package sample.utils.repository;

import sample.models.AttributeRange;
import sample.models.Entry;
import sample.utils.id3.DecisionTree;

import java.util.List;
import java.util.Map;

/**
 * Created by Cristi on 12/17/2016.
 */
public abstract class Repository {
    List<Entry> formattedEntries;
    List<Entry> rawEntries;
    DecisionTree tree;

    public abstract List<String> getAllValuesAsStringList();

    public abstract Map<Long, String> getNamesMap();

    public abstract Map<Long, List<AttributeRange>> getAttributeRange();

//    public List<Entry> getPartialRawEntries(boolean[] attributes) {
//
//    }
}
