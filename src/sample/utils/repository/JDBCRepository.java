package sample.utils.repository;

import sample.models.AttributeRange;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cristi on 12/17/2016.
 */
public class JDBCRepository extends Repository {
    private Connection conn;

    public JDBCRepository() throws Exception {
        this.conn = JDBCUtil.getInstance().getConnection();
        loadData();
    }

    public List<String> getAllValuesAsStringList() {
        Statement statement = null;
        ResultSet rs = null;
        List<String> result = new ArrayList<>();
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery("select * from entry");
            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();
            while (rs.next()) {
                String tempResult = "";
                for (int i=1; i<=colCount; i++) {
                    tempResult+= rs.getString(i) + (i!=colCount ? "," : "");
                }
                result.add(tempResult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Map<Long, String> getNamesMap() {
        Map<Long, String> result = new HashMap<>();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery("select * from name_maps");
            while (rs.next()) {
                result.put(rs.getLong(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Map<Long, List<AttributeRange>> getAttributeRange() {
        Map<Long, List<AttributeRange>> result = new HashMap<>();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery("select * from attribute_range");
            while (rs.next()) {
                Long id = rs.getLong(1);
                if (result.get(id) == null) {
                    result.put(id, new ArrayList<>());
                }
                Float min = rs.getFloat(3);
                if (rs.wasNull()) {
                    min = null;
                }
                Float max = rs.getFloat(4);
                if (rs.wasNull()) {
                    max = null;
                }
                result.get(id).add(new AttributeRange(rs.getString(2), min, max));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
