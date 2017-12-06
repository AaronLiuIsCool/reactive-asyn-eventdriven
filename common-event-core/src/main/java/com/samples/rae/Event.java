package com.samples.rae;

import java.util.Map;

public class Event {

    private String topic;

    private String correlationId;
    private String createdBy;
    private String intendedFor;
    private String nature;
    private String reference;
    private Map<String, String> metaData;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getIntendedFor() {
        return intendedFor;
    }

    public void setIntendedFor(String intendedFor) {
        this.intendedFor = intendedFor;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Map<String, String> getMetaData() {
        return metaData;
    }

    public void setMetaData(Map<String, String> metaData) {
        this.metaData = metaData;
    }

}
