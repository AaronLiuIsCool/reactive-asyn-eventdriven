package com.samples.rae;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Collections;
import java.util.Properties;
import java.util.UUID;

@Configuration
public class ClientConfiguration {


    @Bean
    public Consumer<String, String> kafkaConsumer(Environment environment) {

        Properties properties = kafkaConsumerProperties(environment);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList("test"));

        return consumer;
    }

    @Bean
    public KafkaProducer<String, String> kafkaProducer(Environment environment) {
        return new KafkaProducer<>(kafkaProducerProperties(environment));
    }

    private Properties kafkaProducerProperties(Environment environment) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("kafka.bootstrap.server", "localhost:9092"));
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, environment.getProperty("kafka.client.producer.id", "KafkaDefaultProducer-"+ UUID.randomUUID().toString()));
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;

    }

    private Properties kafkaConsumerProperties(Environment environment) {

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("kafka.bootstrap.server", "localhost:9092"));
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, environment.getProperty("kafka.client.consumer.id", "KafkaDefaultConsumer-"+ UUID.randomUUID().toString()));
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        return properties;

    }
}
