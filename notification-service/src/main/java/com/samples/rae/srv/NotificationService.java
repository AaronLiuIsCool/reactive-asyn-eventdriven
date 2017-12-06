package com.samples.rae.srv;

import io.reactivex.Observable;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public Observable<NotificationConfiguration> sendEmail(NotificationConfiguration notificationConfiguration) {
        return Observable.just(notificationConfiguration)
                .flatMap(notificationConfig -> {
                    if(notificationConfig.canSendEmail()) {
                        System.out.println("Sending Email");
                    }
                    return Observable.just(notificationConfig);
                });
    }

    public Observable<NotificationConfiguration> sendSms(NotificationConfiguration notificationConfiguration) {
        return Observable.just(notificationConfiguration)
                .flatMap(notificationConfig -> {
                    if(notificationConfig.canSendSms()) {
                        System.out.println("Sending SMS");
                    }
                    return Observable.just(notificationConfig);
                });

    }

    public Observable<NotificationConfiguration> sendPushNotification(NotificationConfiguration notificationConfiguration) {
        return Observable.just(notificationConfiguration)
                .flatMap(notificationConfig -> {
                    if(notificationConfig.canSendPushNotification()) {
                        System.out.println("Sending Push Notification");
                    }
                    return Observable.just(notificationConfig);
                });
    }

    public Observable<NotificationConfiguration> sendApplicationNotification(NotificationConfiguration notificationConfiguration) {
        return Observable.just(notificationConfiguration)
                .flatMap(notificationConfig -> {
                    if(notificationConfig.canSendApplicationNotification()) {
                        System.out.println("Sending Application Notification");
                    }
                    return Observable.just(notificationConfig);
                });
    }

    public Observable<NotificationConfiguration> getNotificationConfigurationFor(String username) {
        // mock
        return Observable.just(new NotificationConfiguration(true, true, false, true));
    }
}
