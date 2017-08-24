package com.hawkeye.pitstop.services;

import com.hawkeye.pitstop.config.WebSocketConfig;
import com.hawkeye.pitstop.entities.PitStopEntity;
import com.hawkeye.pitstop.repositories.PitStopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PitStopServiceImpl implements PitStopService{
    private static final Logger log = LoggerFactory.getLogger(PitStopServiceImpl.class);
    private PitStopRepository pitStopRepository;
    private final SimpMessagingTemplate websocket;

    @Autowired
    public PitStopServiceImpl(SimpMessagingTemplate websocket, EntityLinks entityLinks, PitStopRepository pitStopRepository){
        this.pitStopRepository = pitStopRepository;
        this.websocket = websocket;
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
                PitStopEntity saved = pitStopRepository.save(newPitStop);
                log.info(newPitStop.toString());
                //this is here because it's hacky to autowire JPA listeners,
                //and Data Rest Repository eventHandlers don't pickup events from services
                this.websocket.convertAndSend(
                        WebSocketConfig.MESSAGE_PREFIX + "/newPitStopEntity", "new");
                log.info("new entity message sent");
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
