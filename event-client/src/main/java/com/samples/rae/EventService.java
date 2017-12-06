package com.samples.rae;

import com.google.gson.Gson;
import io.reactivex.Observable;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("unchecked")
@Service
public class EventService {

    @Autowired
    private KafkaProducer producer;

    private final Gson gson = new Gson();

    public Observable<Event> fire(Event event) {

        return Observable.just(event)
                .flatMap(eventToBePublished -> {

                    try {

                        ProducerRecord producerRecord = new ProducerRecord(eventToBePublished.getTopic(), gson.toJson(eventToBePublished));
                        producer.send(producerRecord);
                        return Observable.just(event);

                    } catch (Exception e) {

                        producer.flush();
                        return Observable.error(new Exception(e));
                    }

                });
    }

}
