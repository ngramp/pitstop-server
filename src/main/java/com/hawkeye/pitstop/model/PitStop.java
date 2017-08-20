package com.hawkeye.pitstop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PitStop {
    @JsonProperty("pit_in_elapsed_time")
    private Double timeIn;
    @JsonProperty("pit_out_elapsed_time")
    private Double timeOut;

    @Override
    public String toString() {
        return "PitStop{" +
                "timeIn=" + timeIn +
                ", timeOut=" + timeOut +
                '}';
    }

    public Double getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Double timeIn) {
        this.timeIn = timeIn;
    }

    public Double getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Double timeOut) {
        this.timeOut = timeOut;
    }
}
