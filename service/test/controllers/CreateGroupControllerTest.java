package controllers;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response;

import akka.actor.ActorRef;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import play.mvc.Result;
import scala.concurrent.Await;

@RunWith(PowerMockRunner.class)
@PrepareForTest({org.sunbird.Application.class})
@PowerMockIgnore({"javax.management.*", "javax.net.ssl.*", "javax.security.*"})
public class CreateGroupControllerTest extends TestHelper {
  @BeforeClass
  public static void setUp() throws Exception {
    setupMock();
  }
  // TODO - Mock Cassandra and bring this live.
  @Ignore
  @Test
  public void testCreateGroupPasses() {
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("name", "group");
    Map<String, Object> request = new HashMap<>();
    request.put("request", reqMap);
    Result result = performTest("/v1/group/create", "POST", request, headerMap);
    assertTrue(getResponseStatus(result) == Response.Status.OK.getStatusCode());
  }

  @Test
  public void testMandatoryParamGroupName() {
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("description", "group");
    Map<String, Object> request = new HashMap<>();
    request.put("request", reqMap);
    Result result = performTest("/v1/group/create", "POST", request, headerMap);
    assertTrue(getResponseStatus(result) == Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void testGroupNameType() {
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("name", 123);
    Map<String, Object> request = new HashMap<>();
    request.put("request", reqMap);
    Result result = performTest("/v1/group/create", "POST", request, headerMap);
    assertTrue(getResponseStatus(result) == Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void testCreateGroupWithEmptyRequestObject() {
    Map<String, Object> request = new HashMap<>();
    request.put("name", "groupName");
    Result result = performTest("/v1/group/create", "POST", request, headerMap);
    assertTrue(getResponseStatus(result) == Response.Status.BAD_REQUEST.getStatusCode());
  }
}
