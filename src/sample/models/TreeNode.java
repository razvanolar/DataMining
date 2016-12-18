package sample.models;

import java.util.List;

/**
 * Created by Cristi on 12/16/2016.
 */
public abstract class TreeNode {
    public abstract List<TreeNode> getChildren();
    public abstract String getDisplayName();
    public abstract float getResult();
    public abstract int getIndex();
}
