package com.example.kafka_new;

import org.json.JSONObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.BufferedReader;
import java.io.FileReader;

@SpringBootApplication
public class KafkaNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaNewApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
        return args -> {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/data.tsv"));

            String[] fieldNames = reader.readLine().split("\t");

            String line;
            while ((line = reader.readLine()) != null) {

                String[] fields = line.split("\t");

                JSONObject row = new JSONObject();
                for (int i = 0; i < fieldNames.length; i++) {
                    row.put(fieldNames[i], fields[i]);
                }
                kafkaTemplate.send("javad",row.toString());
            }
            reader.close();

        };
    }
}
