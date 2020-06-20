package actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.javadsl.TestKit;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sunbird.actor.UserGroupActor;
import org.sunbird.models.ActorOperations;
import org.sunbird.request.Request;
import org.sunbird.response.Response;

import java.time.Duration;

public class UserGroupActorTest {
    static ActorSystem system;
    private final Props props = Props.create(UserGroupActor.class);

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create("system");
    }

    @AfterClass
    public static void teardown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }
    @Test
    public void testCreateUserGroup() {
        TestKit probe = new TestKit(system);
        ActorRef subject = system.actorOf(props);
        Request reqObj = new Request();
        reqObj.setOperation(ActorOperations.CREATE_GROUP.getValue());
        subject.tell(reqObj, probe.getRef());
        Response res = probe.expectMsgClass(Duration.ofSeconds(10), Response.class);
        Assert.assertTrue(null != res && res.getResponseCode()== 200);
    }


}