package com.samples.rae;

import com.samples.rae.srv.AccountService;
import com.samples.rae.srv.NotificationService;
import com.samples.rae.srv.domain.Account;
import io.reactivex.Observable;
import org.springframework.beans.factory.annotation.Autowired;

@EventListener(topic = "test")
public class SpecialNotificationEventListener {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AccountService accountService;

    @EventMapping(event = "billPaid")
    public void billPaid(Event event) {

        // Only send notification
        accountService.getAccounts(event.getIntendedFor())
                .toList()
                .toObservable()
                .map(accounts -> accounts.stream().map(Account::getAmount)
                        .mapToDouble(value -> value)
                        .sum())
                .flatMap(totalAmount -> {
                    System.out.println("----------------");
                    System.out.println("Total amount: " + totalAmount);
                    if (totalAmount > 1000) {
                        return notificationService.getNotificationConfigurationFor(event.getIntendedFor())
                                .flatMap(notificationService::sendEmail)
                                .flatMap(notificationService::sendPushNotification)
                                .flatMap(notificationService::sendSms)
                                .flatMap(notificationService::sendApplicationNotification);
                    }
                    return Observable.empty();
                })
                .blockingLast();

    }


}
