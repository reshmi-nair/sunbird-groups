package actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.javadsl.TestKit;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.sunbird.BaseException;
import org.sunbird.actor.GroupActor;
import org.sunbird.models.ActorOperations;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.service.GroupService;
import org.sunbird.service.impl.GroupServiceImpl;
import org.sunbird.util.JsonKey;

import java.time.Duration;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;
@RunWith(PowerMockRunner.class)
@PrepareForTest({
        GroupService.class,
        GroupServiceImpl.class
})
@PowerMockIgnore({"javax.management.*"})
public class GroupActorTest {
    static ActorSystem system;
    private final Props props = Props.create(GroupActor.class);
    private static GroupService groupService;

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
        PowerMockito.mockStatic(GroupServiceImpl.class);
        groupService = mock(GroupServiceImpl.class);
        when(GroupServiceImpl.getInstance()).thenReturn(groupService);
    }

    @Test
    public void testCreateGroup() {
        TestKit probe = new TestKit(system);
        ActorRef subject = system.actorOf(props);
        Request reqObj = new Request();
        reqObj.setOperation(ActorOperations.CREATE_GROUP.getValue());
        reqObj.getRequest().put(JsonKey.GROUP_NAME,"TestGroup");
        reqObj.getRequest().put(JsonKey.GROUP_DESC,"TestGroup Description");
        try {
        when(groupService.createGroup(Mockito.anyObject())).thenReturn(Mockito.anyString());
        subject.tell(reqObj, probe.getRef());
        Response res = probe.expectMsgClass(Duration.ofSeconds(10), Response.class);
        Assert.assertTrue(null != res && res.getResponseCode()== 200);
        } catch (BaseException be) {
            Assert.assertTrue(false);
        }
    }


}