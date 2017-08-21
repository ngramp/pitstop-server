package com.hawkeye.pitstop.entities;

import javax.persistence.*;
import java.sql.Time;

@Entity
public class PitStop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer vehicleNumber;
    private Time timeIn;
    private Time timeOut;
    private String comment;


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
