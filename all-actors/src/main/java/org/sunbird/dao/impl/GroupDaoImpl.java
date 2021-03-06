package org.sunbird.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.sunbird.BaseException;
import org.sunbird.cassandra.CassandraOperation;
import org.sunbird.dao.GroupDao;
import org.sunbird.helper.ServiceFactory;
import org.sunbird.models.Group;
import org.sunbird.util.DBUtil;
import org.sunbird.util.JsonKey;

import java.util.Map;

public class GroupDaoImpl implements GroupDao {
    private static final String TABLE_NAME = "group";
    private CassandraOperation cassandraOperation = ServiceFactory.getInstance();
    private ObjectMapper mapper = new ObjectMapper();
    private static GroupDao groupDao = null;

    public static GroupDao getInstance() {
        if (groupDao == null) {
            groupDao = new GroupDaoImpl();
        }
        return groupDao;
    }

    @Override
    public String createGroup(Group groupObj) throws BaseException {
        Map<String, Object> map = mapper.convertValue(groupObj, Map.class);
        cassandraOperation.insertRecord(DBUtil.KEY_SPACE_NAME, TABLE_NAME, map);
        return (String) map.get(JsonKey.ID);
    }
}
