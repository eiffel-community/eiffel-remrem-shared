package com.ericsson.eiffel.remrem.shared;


import com.google.gson.JsonObject;

public interface MsgService {
    String generateMsg(String msgType, JsonObject bodyJson);
}
