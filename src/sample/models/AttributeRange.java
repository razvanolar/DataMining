package sample.models;

/**
 * Created by Cristi on 12/16/2016.
 */
public class AttributeRange {
    String displayName;
    Float min;
    Float max;

    public AttributeRange(String displayName, Float min, Float max) {
        this.displayName = displayName;
        this.min = min;
        this.max = max;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Float getMin() {
        return min;
    }

    public void setMin(Float min) {
        this.min = min;
    }

    public Float getMax() {
        return max;
    }

    public void setMax(Float max) {
        this.max = max;
    }
}
