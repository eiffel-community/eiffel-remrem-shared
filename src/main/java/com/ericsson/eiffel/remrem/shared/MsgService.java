package com.ericsson.eiffel.remrem.shared;

import com.google.gson.JsonObject;

public interface MsgService {

    /**
     * Generates the event as string from the input json Object based on the message type.
     * @param String msgType
     * @param JsonObject bodyJson
     * @return the generated event as string format
     */
    String generateMsg(String msgType, JsonObject bodyJson);

    /**
     * Returns the Event Id from the json object. 
     * @param JsonObject bodyJson
     * @return the eventId from json object if event id not available then returns the null value
     */
    String getEventId(JsonObject bodyJson);
}
