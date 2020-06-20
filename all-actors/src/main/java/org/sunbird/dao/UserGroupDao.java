package org.sunbird.dao;

import org.sunbird.BaseException;
import org.sunbird.models.UserGroup;

/**
 * This interface will have all methods required for user service api.
 *
 */
public interface UserGroupDao {

        /**
         * This method will create group and return groupId as success response or throw
         * ProjectCommonException.
         *
         * @param group Group Details.
         * @return Group ID.
         */
        String createUserGroup(UserGroup group) throws BaseException;
}
