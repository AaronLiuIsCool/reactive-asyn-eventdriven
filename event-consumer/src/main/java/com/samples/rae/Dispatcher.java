package com.samples.rae;

import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class Dispatcher {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Consumer consumer;

    private final Gson gson = new Gson();


    public void run() {
        final int giveUp = 100;
        int noRecordsCount = 0;

        while (true) {
            final ConsumerRecords<String, String> consumerRecords = consumer.poll(100000000);

            if (consumerRecords.count() == 0) {
                noRecordsCount++;
                if (noRecordsCount > giveUp) break;
                else continue;
            }

            consumerRecords.forEach(record -> {

                Event event = gson.fromJson(record.value(), Event.class);
                ConsumerDataStore.getMappingFor(event.getTopic(), event.getNature())
                        .stream()
                        .map(method -> {
                            try {
                                method.invoke(applicationContext.getBean(method.getDeclaringClass()), event);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return method;
                        })
                        .collect(Collectors.toList());
            });

            try {
                consumer.commitAsync();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        System.out.println("Consumer closed -----------------------------------------");
        consumer.close();
    }

}
