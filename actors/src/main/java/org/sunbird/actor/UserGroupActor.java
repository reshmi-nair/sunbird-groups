package org.sunbird.group;

import org.sunbird.BaseActor;
import org.sunbird.akka.core.ActorConfig;
import org.sunbird.models.ActorOperations;
import org.sunbird.request.Request;

import org.apache.log4j.Logger;

@ActorConfig(
        tasks = {"createGroup", "updateGroup"},
        asyncTasks = {}
)
public class UserGroupActor extends BaseActor {

    private Logger logger = Logger.getLogger(UserGroupActor.class);

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

    }

}
