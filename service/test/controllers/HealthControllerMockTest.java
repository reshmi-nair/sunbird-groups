package controllers;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

import akka.actor.ActorRef;
import javax.ws.rs.core.Response;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.sunbird.exception.BaseException;
import play.mvc.Http;
import play.mvc.Result;
import scala.concurrent.Await;

@RunWith(PowerMockRunner.class)
@PrepareForTest({org.sunbird.Application.class, BaseController.class, ActorRef.class, Await.class})
@PowerMockIgnore({"javax.management.*", "javax.net.ssl.*", "javax.security.*"})
public class HealthControllerMockTest extends TestHelper {

  @BeforeClass
  public static void setUp() throws Exception {
    setupMock();
    HealthController healthController = Mockito.mock(HealthController.class);
    when(healthController.createSBRequest(Mockito.any(Http.Request.class),Mockito.anyString()))
            .thenThrow(new RuntimeException("induced due to buggy server impl"));

  }
  @Test
  public void testOnServerHandlerPasses() throws BaseException {


    Result result = performTest("/service/health", "GET", null, headerMap);
    System.out.println(getResponseStatus(result));
    assertEquals(Response.Status.fromStatusCode(400).getStatusCode(), getResponseStatus(result));
  }
}
