package controllers;

import static org.powermock.api.mockito.PowerMockito.*;
import static play.test.Helpers.route;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import java.util.WeakHashMap;
import org.apache.commons.lang3.StringUtils;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.sunbird.exception.BaseException;
import org.sunbird.helper.CassandraConnectionManager;
import org.sunbird.helper.CassandraConnectionManagerImpl;
import org.sunbird.helper.CassandraConnectionMngrFactory;
import org.sunbird.util.JsonKey;
import play.Application;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
        CassandraConnectionMngrFactory.class
})
/** This a helper class for All the Controllers Test */
public class TestHelper {
  // One and only app
  private static Application app;

  protected static org.sunbird.Application sbApp;

  // Let test cases create one if needed. This will be private.
  private final ObjectMapper mapperObj = new ObjectMapper();

  // Only for derivations
  protected Map<String, String> headerMap;

  private static final CassandraConnectionManager cassandraConnectionManager = mock(CassandraConnectionManagerImpl.class);;

  public TestHelper() {
    headerMap = new WeakHashMap<>();
    headerMap.put(JsonKey.VER, "1.0");
    headerMap.put(JsonKey.ID, "api.test.id");
    headerMap.put(JsonKey.REQUEST_MESSAGE_ID, this.getClass().getSimpleName());
  }

  /**
   * This method will perform a request call.
   *
   * @param url
   * @param method
   * @param requestMap
   * @param headerMap
   * @return Result
   */
  public Result performTest(String url, String method, Map requestMap, Map headerMap) {
    setupCassandraMock();
    String data = mapToJson(requestMap);
    Http.RequestBuilder req;
    if (StringUtils.isNotBlank(data) && !requestMap.isEmpty()) {
      JsonNode json = Json.parse(data);
      req = new Http.RequestBuilder().bodyJson(json).uri(url).method(method);
    } else {
      req = new Http.RequestBuilder().uri(url).method(method);
    }
    req.header("Content-Type", "application/json");
    Result result = route(app, req);
    return result;
  }
  public static void setupCassandraMock() {
    PowerMockito.mockStatic(CassandraConnectionMngrFactory.class);
    when(CassandraConnectionMngrFactory.getInstance()).thenReturn(cassandraConnectionManager);
    try {
      doNothing().when(cassandraConnectionManager).createConnection(Mockito.anyObject());
    }catch (Exception e){}
  }
  public static void setupMock() throws BaseException {
    setupCassandraMock();
    app = Helpers.fakeApplication();
    sbApp = mock(org.sunbird.Application.class);
    PowerMockito.mockStatic(org.sunbird.Application.class);
    when(org.sunbird.Application.getInstance()).thenReturn(sbApp);
    sbApp.init();
  }

  /**
   * This method is responsible for converting map to json
   *
   * @param map
   * @return String
   */
  public String mapToJson(Map map) {
    String jsonResp = "";

    if (map != null) {
      try {
        jsonResp = mapperObj.writeValueAsString(map);
      } catch (IOException e) {
      }
    }
    return jsonResp;
  }

  /**
   * This method is used to return the status Code for the perform request
   *
   * @param result
   * @return
   */
  public int getResponseStatus(Result result) {
    return result.status();
  }
}
