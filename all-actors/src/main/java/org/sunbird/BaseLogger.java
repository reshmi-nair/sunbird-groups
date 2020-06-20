package org.sunbird;

import org.slf4j.MDC;
import org.sunbird.util.JsonKey;

import java.util.Map;

public class BaseLogger {


    /**
     * sets requestId in Sl4j MDC
     * @param trace
     */
    public void setReqId(Map<String, Object> trace) {
        MDC.clear();
        MDC.put(JsonKey.REQ_ID, (String) trace.get(JsonKey.REQ_ID));
    }

    public String getReqId() {
        return MDC.get(JsonKey.REQ_ID);
    }
}
