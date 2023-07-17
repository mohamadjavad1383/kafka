package com.example.kafka_new;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

@Component
public class KafkaProducer implements CommandLineRunner{
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("file name:");
        String fileName = scanner.nextLine();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        String[] fieldNames = reader.readLine().split("\t");

        String line;
        while ((line = reader.readLine()) != null) {

            String[] fields = line.split("\t");

            JSONObject row = new JSONObject();
            for (int i = 0; i < fieldNames.length; i++) {
                row.put(fieldNames[i], fields[i]);
            }
            kafkaTemplate.send("actor", row.toString());
        }
        reader.close();
    }
}
