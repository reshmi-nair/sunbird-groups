package org.sunbird.service.impl;

import org.sunbird.BaseException;
import org.sunbird.dao.UserGroupDao;
import org.sunbird.dao.impl.UserGroupDaoImpl;
import org.sunbird.models.UserGroup;
import org.sunbird.service.UserGroupService;

import java.util.UUID;

public class UserGroupServiceImpl implements UserGroupService {

    private static UserGroupDao groupDao = UserGroupDaoImpl.getInstance();
    private static UserGroupService groupService = null;
    public static UserGroupService getInstance() {
        if (groupService == null) {
            groupService = new UserGroupServiceImpl();
        }
        return groupService;
    }

    @Override
    public String createUserGroup(String groupName) throws BaseException {

        UserGroup group = new UserGroup();
        group.setName(groupName);
        group.setId(UUID.randomUUID().toString());
        String groupId = groupDao.createUserGroup(group);
        return groupId;
    }
}
