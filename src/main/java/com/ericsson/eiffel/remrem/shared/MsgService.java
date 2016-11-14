package com.ericsson.eiffel.remrem.shared;


import com.google.gson.JsonObject;

public interface MsgService {
    String generateMsg(String msgType, JsonObject bodyJson);
    String validateMsg(String msgType, JsonObject bodyJson);
    String msgServiceName();
}
