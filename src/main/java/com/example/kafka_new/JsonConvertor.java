package com.example.kafka_new;

import com.example.kafka_new.actor.Actor;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;

@AllArgsConstructor
public class JsonConvertor {
    private JSONObject object;

    public Actor createActor() {
        String primaryName = getPrimaryName();
        Integer birthYear = getBirthYear();
        Integer deathYear = getDeathYear();
        String primaryProfession = getPrimaryProfession();
        String knownForTitles = getKnownForTitles();
        return new Actor(primaryName, birthYear, deathYear, primaryProfession, knownForTitles);
    }

    private String getKnownForTitles() {
        String knownForTitles;
        try {
            knownForTitles = object.getString("knownForTitles");
        } catch (JSONException e) {
            knownForTitles = null;
        }
        return knownForTitles;
    }

    private String getPrimaryProfession() {
        String primaryProfession;
        try {
            primaryProfession = object.getString("primaryProfession");
        } catch (JSONException e) {
            primaryProfession = null;
        }
        return primaryProfession;
    }

    private Integer getDeathYear() {
        Integer deathYear;
        try {
            deathYear = object.getInt("deathYear");
        } catch (JSONException e) {
            deathYear = null;
        }
        return deathYear;
    }

    private Integer getBirthYear() {
        Integer birthYear;
        try {
            birthYear = object.getInt("birthYear");
        } catch (JSONException e) {
            birthYear = null;
        }
        return birthYear;
    }

    private String getPrimaryName() {
        String primaryName;
        try {
            primaryName = object.getString("primaryName");
        } catch (JSONException e) {
            primaryName = null;
        }
        return primaryName;
    }
}
