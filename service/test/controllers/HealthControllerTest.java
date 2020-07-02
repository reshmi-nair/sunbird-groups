package controllers;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import play.mvc.Result;

@RunWith(PowerMockRunner.class)
@PrepareForTest({org.sunbird.Application.class})
@PowerMockIgnore({"javax.management.*", "javax.net.ssl.*", "javax.security.*"})
public class HealthControllerTest extends TestHelper {

  @BeforeClass
  public static void setUp() throws Exception {
    setupMock();
  }

  @Test
  public void testGetHealthPasses() {
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("accept", "yes");
    Result result = performTest("/health", "GET", reqMap, headerMap);
    System.out.println(getResponseStatus(result) +" : "+Response.Status.OK.getStatusCode());
    assertTrue(getResponseStatus(result) == Response.Status.OK.getStatusCode());
  }
  // TODO - Mock Cassandra and bring this live.
  @Ignore
  @Test
  public void testGetServiceHealthPasses() {
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("accept", "yes");
    Result result = performTest("/service/health", "GET", reqMap, headerMap);
    assertTrue(getResponseStatus(result) == Response.Status.OK.getStatusCode());
  }

  @Test
  public void testPostHealthFails() {
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("accept", "yes");
    Result result = performTest("/health", "POST", reqMap, headerMap);
    assertTrue(getResponseStatus(result) == Response.Status.NOT_FOUND.getStatusCode());
  }
}
