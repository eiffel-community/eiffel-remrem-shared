package com.ericsson.eiffel.remrem.shared;


import com.google.gson.JsonObject;

public interface MsgService {
    
    String EVENT_ID = "eventId";
    String ID = "id";
    String META = "meta";
    String EIFFEL_MESSAGE_VERSIONS = "eiffelMessageVersions";
    
    String generateMsg(String msgType, JsonObject bodyJson);
    String getEventId(JsonObject bodyJson);
}
