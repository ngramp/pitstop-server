package com.hawkeye.pitstop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LiveFeed {

    @JsonProperty("lap_number")
    private Integer lapNumber;
    @JsonProperty("elapsed_time")
    private Double elapsedTime;
    @JsonProperty("time_of_day")
    private Double timeOfDay;
    private List<Vehicle> vehicles;

    @Override
    public String toString() {
        return "LiveFeed{" +
                "lapNumber=" + lapNumber +
                ", elapsedTime=" + elapsedTime +
                ", timeOfDay=" + timeOfDay +
                ", vehicles=" + vehicles +
                '}';
    }

    public Integer getLapNumber() {
        return lapNumber;
    }

    public void setLapNumber(Integer lapNumber) {
        this.lapNumber = lapNumber;
    }

    public Double getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Double elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public Double getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(Double timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
