package org.sunbird.service.impl;

import org.sunbird.common.exception.ProjectCommonException;
import org.sunbird.common.models.util.ProjectUtil;
import org.sunbird.common.responsecode.ResponseCode;
import org.sunbird.dao.UserGroupDao;
import org.sunbird.dao.impl.UserGroupDaoImpl;
import org.sunbird.models.UserGroup;
import org.sunbird.service.UserGroupService;

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
    public String createUserGroup(String groupName) {

        UserGroup group = new UserGroup();
        group.setName(groupName);
        group.setId(ProjectUtil.generateUniqueId());
        String groupId = groupDao.createUserGroup(group);
        return groupId;
    }
}
