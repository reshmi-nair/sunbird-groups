package org.sunbird.actor;

import org.sunbird.BaseActor;
import org.sunbird.BaseException;
import org.sunbird.actor.core.ActorConfig;
import org.sunbird.models.UserGroup;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.service.UserGroupService;
import org.sunbird.service.impl.UserGroupServiceImpl;
import org.sunbird.util.JsonKey;

@ActorConfig(
        tasks = {"createGroup", "updateGroup"},
        asyncTasks = {}
)
public class UserGroupActor extends BaseActor {

    private UserGroupService groupService = UserGroupServiceImpl.getInstance();


    @Override
    public void onReceive(Request request) throws Throwable {
        String operation = request.getOperation();
        switch (operation) {
            case "createGroup": // create Group
                createGroup(request);
                break;
            case "updateGroup":
                //updateGroup(request);
                break;
            default:
                onReceiveUnsupportedMessage("UserGroupActor");
        }
    }
    /**
     * This method will create group in cassandra.
     *
     * @param actorMessage
     */
    private void createGroup(Request actorMessage) throws BaseException {
        logger.info("CreateGroup method call");

        UserGroup group = new UserGroup();
        group.setName((String)actorMessage.getRequest().get(JsonKey.GROUP_NAME));
        group.setDescription((String)actorMessage.getRequest().get(JsonKey.GROUP_DESC));
        String groupId = groupService.createUserGroup(group);
        Response response = new Response();
        response.put(JsonKey.GROUP_ID, groupId);
        sender().tell(response, self());
    }

}
