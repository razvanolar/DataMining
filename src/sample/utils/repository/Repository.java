package sample.utils.repository;

import sample.models.AttributeRange;
import sample.models.Entry;
import sample.models.FormattedEntry;
import sample.models.RawEntry;
import sample.utils.id3.DecisionTree;
import sample.utils.id3.ID3Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Cristi on 12/17/2016.
 */
public abstract class Repository {
    private List<FormattedEntry> formattedEntries;
    private List<RawEntry> rawEntries;
    private DecisionTree tree;

    protected void loadData() {
        ID3Utils.namesMap = getNamesMap();
        ID3Utils.ATTR_NUMBER = ID3Utils.namesMap.size() - 1;
        ID3Utils.attributeRangeMap = getAttributeRange();
        List<String> strings = getAllValuesAsStringList();
        formattedEntries = new ArrayList<>();
        rawEntries = new ArrayList<>();
        for (String string : strings) {
            formattedEntries.add(new FormattedEntry(string));
            rawEntries.add(new RawEntry(string));
        }
    }

    public void reloadData() {
        loadData();
    }

    public abstract List<String> getAllValuesAsStringList();

    public abstract Map<Long, String> getNamesMap();

    public abstract Map<Long, List<AttributeRange>> getAttributeRange();

    public List<Entry> getPartialRawEntries(boolean[] attributes) {
        List<Entry> result = new ArrayList<>();
        for (Entry rawEntry : rawEntries) {
            String content = "";
            for (int i=0; i<rawEntry.getValues().size(); i++) {
                if (attributes[i]) {
                    content += rawEntry.getValues().get(i) + (i < rawEntry.getValues().size() - 1 ? "" : ",");
                }
            }
            result.add(new RawEntry(content));
        }
        return result;
    }

    public List<FormattedEntry> getFormattedEntries() {
        return formattedEntries;
    }

    public List<RawEntry> getRawEntries() {
        return rawEntries;
    }

    public DecisionTree getTree() {
        return tree;
    }

    public void setTree(DecisionTree tree) {
        this.tree = tree;
    }
}
