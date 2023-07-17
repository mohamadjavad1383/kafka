package com.example.kafka_new;

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

    @KafkaListener(topics = "actor", groupId = "group-Id")
    private void writeToDB(String data) {
        WriteToDataBase(data);
    }

    @KafkaListener(topics = "actor", groupId = "group-Id2")
    private void writeToDB2(String data) {
        WriteToDataBase(data);

    }

    private void WriteToDataBase(String data) {
        JSONObject object = null;
        try {
            object = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonConvertor convertor = new JsonConvertor(object);
        actorRepository.save(convertor.createActor());
    }
}
