package controllers;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import play.mvc.Result;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@RunWith(PowerMockRunner.class)
@PrepareForTest({org.sunbird.Application.class})
@PowerMockIgnore({"javax.management.*", "javax.net.ssl.*", "javax.security.*"})
public class ReadGroupControllerTest extends TestHelper {

    @BeforeClass
    public static void setUp() throws Exception {
        setupMock();
    }
    // TODO - Mock Cassandra and bring this live.
    @Ignore
    @Test
    public void testReadGroupPasses() {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("groupId", "group1");
        Map<String, Object> request = new HashMap<>();
        request.put("request", reqMap);
        Result result = performTest("/v1/group/read/:group1", "GET", request, headerMap);
        System.out.println(getResponseStatus(result) +" : "+Response.Status.OK.getStatusCode());
        assertTrue(getResponseStatus(result) == Response.Status.OK.getStatusCode());
    }

}
