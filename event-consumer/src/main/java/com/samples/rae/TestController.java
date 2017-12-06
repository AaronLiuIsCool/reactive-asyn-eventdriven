package com.samples.rae;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
public class TestController {

    @Autowired
    EventService eventService;

    @Autowired
    Dispatcher dispatcher;


    @RequestMapping("/")
    public String test() {

        Event event = new Event();
        event.setTopic("test");
        event.setCorrelationId(UUID.randomUUID().toString());
        event.setIntendedFor("accountId");
        event.setNature("billPaid");

        eventService.fire(event).blockingLast();

        return "done";
    }

}
