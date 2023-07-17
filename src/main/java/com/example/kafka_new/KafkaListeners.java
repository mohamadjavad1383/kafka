package com.example.kafka_new;

import com.example.kafka_new.actor.Actor;
import com.example.kafka_new.actor.ActorRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    private final ActorRepository actorRepository;

    @Autowired
    public KafkaListeners(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @KafkaListener(topics = "javad", groupId = "group-Id")
    private void writeToDB(String data) {
        db(data);
    }

    @KafkaListener(topics = "javad", groupId = "group-Id2")
    private void writeToDB2(String data) {
        db(data);

    }

    private void db(String data) {
        JSONObject object = null;
        try {
            object = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String primaryName;
        try {
            primaryName = object.getString("primaryName");
        } catch (JSONException e) {
            primaryName = null;
        }
        Integer birthYear;
        try {
            birthYear = object.getInt("birthYear");
        } catch (JSONException e) {
            birthYear = null;
        }
        Integer deathYear;
        try {
            deathYear = object.getInt("deathYear");
        } catch (JSONException e) {
            deathYear = null;
        }
        String primaryProfession;
        try {
            primaryProfession = object.getString("primaryProfession");
        } catch (JSONException e) {
            primaryProfession = null;
        }
        String knownForTitles;
        try {
            knownForTitles = object.getString("knownForTitles");
        } catch (JSONException e) {
            knownForTitles = null;
        }

        actorRepository.save(new Actor(primaryName, birthYear, deathYear, primaryProfession, knownForTitles));
    }
}
