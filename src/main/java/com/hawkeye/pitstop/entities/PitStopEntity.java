package com.hawkeye.pitstop.entities;

import javax.persistence.*;

@Entity
public class PitStopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer vehicleNumber;
    private Double timeIn;
    private Double timeOut;
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(Integer vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
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

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PitStopEntity that = (PitStopEntity) o;

        if (getVehicleNumber() != null ? !getVehicleNumber().equals(that.getVehicleNumber()) : that.getVehicleNumber() != null)
            return false;
        return getTimeIn() != null ? getTimeIn().equals(that.getTimeIn()) : that.getTimeIn() == null;
    }

    @Override
    public int hashCode() {
        int result = getVehicleNumber() != null ? getVehicleNumber().hashCode() : 0;
        result = 31 * result + (getTimeIn() != null ? getTimeIn().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PitStopEntity{" +
                "id=" + id +
                ", vehicleNumber=" + vehicleNumber +
                ", timeIn=" + timeIn +
                ", timeOut=" + timeOut +
                ", comment='" + comment + '\'' +
                '}';
    }
}
