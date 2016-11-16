package com.ericsson.eiffel.remrem.shared;

import com.google.gson.JsonObject;

public interface MsgService {

    String EVENT_ID = "eventId";
    String ID = "id";
    String META = "meta";
    String EIFFEL_MESSAGE_VERSIONS = "eiffelMessageVersions";

    /**
     * 
     * @param msgType
     * @param bodyJson
     * @return
     */
    String generateMsg(String msgType, JsonObject bodyJson);

    /**
     * Get the event id from json object.
     * @param bodyJson
     * @return
     */
    String getEventId(JsonObject bodyJson);
}
