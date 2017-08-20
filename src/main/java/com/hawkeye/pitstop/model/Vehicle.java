package com.hawkeye.pitstop.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Vehicle {
    @JsonProperty("vehicle_number")
    private Integer vehicleNumber;
    @JsonProperty("pit_stops")
    private List<PitStop> pitStops;
    @JsonProperty("vehicle_elapsed_time")
    private Double vehicleElapsedTime;

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleNumber=" + vehicleNumber +
                ", pitStops=" + pitStops +
                ", vehicleElapsedTime=" + vehicleElapsedTime +
                '}';
    }

    public Integer getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(Integer vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public List<PitStop> getPitStops() {
        return pitStops;
    }

    public void setPitStops(List<PitStop> pitStops) {
        this.pitStops = pitStops;
    }

    public Double getVehicleElapsedTime() {
        return vehicleElapsedTime;
    }

    public void setVehicleElapsedTime(Double vehicleElapsedTime) {
        this.vehicleElapsedTime = vehicleElapsedTime;
    }
}
