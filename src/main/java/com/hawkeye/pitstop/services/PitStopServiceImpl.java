package com.hawkeye.pitstop.services;

import com.hawkeye.pitstop.entities.PitStopEntity;
import com.hawkeye.pitstop.repositories.PitStopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class PitStopServiceImpl implements PitStopService{
    private static final Logger log = LoggerFactory.getLogger(PitStopServiceImpl.class);

    private PitStopRepository pitStopRepository;

    @Autowired
    public PitStopServiceImpl(PitStopRepository pitStopRepository){
        this.pitStopRepository = pitStopRepository;
    }


    @Override
    public List<PitStopEntity> getAllPitStops() {
        return pitStopRepository.findAll();
    }

    @Override
    public PitStopEntity getPitStop(long id) {
        return pitStopRepository.findOne(id);
    }

    @Override
    public void createOrUpdate(List<PitStopEntity> pitStopEntities) {
        //pretty sure the default crud methods of jpaRepository already do this
        for(PitStopEntity newPitStop : pitStopEntities){
            //relying on equals method for vehicle number and pitStopIn time
            if(!pitStopRepository.findAll().contains(newPitStop)){
                pitStopRepository.save(newPitStop);
                log.info(newPitStop.toString());
            }
        }
        pitStopRepository.flush();
    }

    @Override
    public void createOrUpdate(PitStopEntity pitStopEntity) {
        if(!pitStopRepository.findAll().contains(pitStopEntity)){
            pitStopRepository.saveAndFlush(pitStopEntity);
        }
    }
}
