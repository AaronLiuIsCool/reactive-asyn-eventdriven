package com.samples.rae.srv;

public class NotificationConfiguration {
    
    private final boolean canSendEmail;
    private final boolean canSendSms;
    private final boolean canSendPushNotification;
    private final boolean canSendApplicationNotification;

    public NotificationConfiguration(boolean canSendEmail, boolean canSendSms, boolean canSendPushNotification, boolean canSendApplicationNotification) {
        this.canSendEmail = canSendEmail;
        this.canSendSms = canSendSms;
        this.canSendPushNotification = canSendPushNotification;
        this.canSendApplicationNotification = canSendApplicationNotification;
    }

    public boolean canSendEmail() {
        return canSendEmail;
    }

    public boolean canSendSms() {
        return canSendSms;
    }

    public boolean canSendPushNotification() {
        return canSendPushNotification;
    }

    public boolean canSendApplicationNotification() {
        return canSendApplicationNotification;
    }
}
