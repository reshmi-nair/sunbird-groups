package controllers.group;

import controllers.BaseController;
import org.sunbird.models.ActorOperations;
import org.sunbird.request.Request;
import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;

public class UserGroupController extends BaseController {

    public CompletionStage<Result> createGroup() {
        CompletionStage<Result> response = handleRequest(request(),
                request -> {
                    Request req = (Request) request;
                    // new CertValidator().validateGenerateCertRequest(req);
                    return null;
                },
                ActorOperations.CREATE_GROUP.getValue());
        return response;
    }

}
