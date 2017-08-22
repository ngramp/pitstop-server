package com.hawkeye.pitstop.services;

import com.hawkeye.pitstop.entities.PitStopEntity;

import java.util.List;
public interface PitStopService {
    List<PitStopEntity> getAllPitStops();
    PitStopEntity getPitStop(long id);
    void createOrUpdate(List<PitStopEntity> pitStopEntities);
    void createOrUpdate(PitStopEntity pitStopEntity);
}
