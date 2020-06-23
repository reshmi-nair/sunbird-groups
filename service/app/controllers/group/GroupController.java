package controllers.group;

import controllers.BaseController;
import org.sunbird.models.ActorOperations;
import org.sunbird.request.Request;
import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;

public class GroupController extends BaseController {

    public CompletionStage<Result> createGroup(Http.Request httpRequest) {
        CompletionStage<Result> response = handleRequest(httpRequest,
                request -> {
                    Request req = (Request) request;
                    return null;
                },
                ActorOperations.CREATE_GROUP.getValue());
        return response;
    }

}
