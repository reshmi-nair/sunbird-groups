package org.sunbird.actor;

import org.sunbird.BaseActor;
import org.sunbird.akka.core.ActorConfig;
import org.sunbird.response.Response;
import org.sunbird.request.Request;

import org.apache.log4j.Logger;
import org.sunbird.service.UserGroupService;
import org.sunbird.service.impl.UserGroupServiceImpl;
import org.sunbird.util.JsonKey;

@ActorConfig(
        tasks = {"createGroup", "updateGroup"},
        asyncTasks = {}
)
public class UserGroupActor extends BaseActor {

    private Logger logger = Logger.getLogger(UserGroupActor.class);
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
    private void createGroup(Request actorMessage) {
        logger.info("CreateGroup method call");
      //  String groupId = groupService.createUserGroup("Group1");
        Response response = new Response();
        response.put(JsonKey.GROUP_ID, "groupId");
        sender().tell(response, self());
    }

}
