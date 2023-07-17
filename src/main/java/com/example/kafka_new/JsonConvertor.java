package com.example.kafka_new;

import com.example.kafka_new.actor.Actor;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;

@AllArgsConstructor
public class JsonConvertor {
    private JSONObject object;

    public Actor createActor() {
        String primaryName = getStringByName("primaryName");
        Integer birthYear = getIntByName("birthYear");
        Integer deathYear = getIntByName("deathYear");
        String primaryProfession = getStringByName("primaryProfession");
        String knownForTitles = getStringByName("knownForTitles");
        return new Actor(primaryName, birthYear, deathYear, primaryProfession, knownForTitles);
    }

    private String getStringByName(String name) {
        try {
            return object.getString(name);
        } catch (JSONException e) {
            return null;
        }
    }

    private Integer getIntByName(String name) {
        try {
            return object.getInt(name);
        } catch (JSONException e) {
            return null;
        }
    }
}
