package actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.javadsl.TestKit;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.sunbird.BaseException;
import org.sunbird.actor.UserGroupActor;
import org.sunbird.helper.ServiceFactory;
import org.sunbird.models.ActorOperations;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.service.UserGroupService;
import org.sunbird.service.impl.UserGroupServiceImpl;

import java.time.Duration;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;
@RunWith(PowerMockRunner.class)
@PrepareForTest({
        UserGroupService.class,
        UserGroupServiceImpl.class
})
@PowerMockIgnore({"javax.management.*"})
public class UserGroupActorTest {
    static ActorSystem system;
    private final Props props = Props.create(UserGroupActor.class);
    private static UserGroupService groupService;

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create("system");
    }

    @AfterClass
    public static void teardown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    @Before
    public void beforeEachTest() {
        groupService = mock(UserGroupServiceImpl.class);
    }
    @Ignore
    @Test(expected = BaseException.class)
    public void testCreateUserGroup() {
        TestKit probe = new TestKit(system);
        ActorRef subject = system.actorOf(props);
        Request reqObj = new Request();
        reqObj.setOperation(ActorOperations.CREATE_GROUP.getValue());
        try {
        when(groupService.createUserGroup(Mockito.anyObject()))
                .thenReturn(Mockito.anyString());
        subject.tell(reqObj, probe.getRef());
        Response res = probe.expectMsgClass(Duration.ofSeconds(10), Response.class);
        Assert.assertTrue(null != res && res.getResponseCode()== 200);
        } catch (BaseException be) {
            Assert.assertTrue(false);
        }
    }


}