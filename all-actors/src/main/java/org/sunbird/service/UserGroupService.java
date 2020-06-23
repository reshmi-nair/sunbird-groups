package org.sunbird.service;

import org.sunbird.BaseException;
import org.sunbird.models.UserGroup;

public interface UserGroupService {

    String createUserGroup(UserGroup group) throws BaseException;
}
