package sample.utils.repository;

import sample.models.AttributeRange;

import java.util.List;
import java.util.Map;

/**
 * Created by Cristi on 12/17/2016.
 */
public interface Repository {
    List<String> getAllValuesAsStringList();
    Map<Long, String> getNamesMap();
    Map<Long, List<AttributeRange>> getAttributeRange();

}
