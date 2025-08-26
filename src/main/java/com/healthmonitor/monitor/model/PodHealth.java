package com.healthmonitor.monitor.model;

public class PodHealth {
    private String podUrl;
    private String status;
    private Long responseTimeMs;

    public PodHealth() {}

    public PodHealth(String podUrl, String status, Long responseTimeMs) {
        this.podUrl = podUrl;
        this.status = status;
        this.responseTimeMs = responseTimeMs;
    }

    public String getPodUrl() {
        return podUrl;
    }

    public void setPodUrl(String podUrl) {
        this.podUrl = podUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getResponseTimeMs() {
        return responseTimeMs;
    }

    public void setResponseTimeMs(Long responseTimeMs) {
        this.responseTimeMs = responseTimeMs;
    }
}