package com.example.demo.incident;

public class RcaResponse {

    private String topError;
    private String likelyIncident;
    private String rootCause;
    private String resolution;

    public RcaResponse(
            String topError,
            String likelyIncident,
            String rootCause,
            String resolution) {

        this.topError = topError;
        this.likelyIncident = likelyIncident;
        this.rootCause = rootCause;
        this.resolution = resolution;
    }

    public String getTopError() {
        return topError;
    }

    public String getLikelyIncident() {
        return likelyIncident;
    }

    public String getRootCause() {
        return rootCause;
    }

    public String getResolution() {
        return resolution;
    }
}