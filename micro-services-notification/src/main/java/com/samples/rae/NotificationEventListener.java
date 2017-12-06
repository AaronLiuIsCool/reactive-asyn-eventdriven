package com.samples.rae;

import com.samples.rae.srv.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;

@EventListener(topic = "test")
public class NotificationEventListener {

    @Autowired
    private NotificationService notificationService;

    @EventMapping(event = "billPaid")
    public void billPaid(Event event) {

        notificationService.getNotificationConfigurationFor(event.getIntendedFor())
                .flatMap(notificationService::sendEmail)
                .flatMap(notificationService::sendPushNotification)
                .flatMap(notificationService::sendSms)
                .flatMap(notificationService::sendApplicationNotification)
                .blockingLast();

    }


}
