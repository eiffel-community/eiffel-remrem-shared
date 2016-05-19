package com.ericsson.eiffel.remrem.message.services;


import com.google.gson.JsonObject;

public interface MsgService {
    String generateMsg(String msgType, JsonObject bodyJson);
}
