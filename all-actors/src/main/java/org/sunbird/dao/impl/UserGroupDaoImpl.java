package org.sunbird.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.sunbird.BaseException;
import org.sunbird.cassandra.CassandraOperation;
import org.sunbird.dao.UserGroupDao;
import org.sunbird.helper.ServiceFactory;
import org.sunbird.models.UserGroup;
import org.sunbird.util.DBUtil;
import org.sunbird.util.JsonKey;

import java.util.Map;

public class UserGroupDaoImpl implements UserGroupDao{
    private static final String TABLE_NAME = "user_group";
    private CassandraOperation cassandraOperation = ServiceFactory.getInstance();
    private ObjectMapper mapper = new ObjectMapper();
    private static UserGroupDao userGroupDao = null;

    public static UserGroupDao getInstance() {
        if (userGroupDao == null) {
            userGroupDao = new UserGroupDaoImpl();
        }
        return userGroupDao;
    }

    @Override
    public String createUserGroup(UserGroup group) throws BaseException {
        Map<String, Object> map = mapper.convertValue(group, Map.class);
        cassandraOperation.insertRecord(DBUtil.KEY_SPACE_NAME, TABLE_NAME, map);
        return (String) map.get(JsonKey.ID);
    }
}
