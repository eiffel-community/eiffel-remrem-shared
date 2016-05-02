package com.ericsson.eiffel.remrem.message.services;


import com.google.gson.JsonObject;

import java.util.Map;

public interface MsgService {
    String generateMsg(String msgType, JsonObject msgNodes, Map<String, String> eventParams);
}
