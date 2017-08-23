package com.hawkeye.pitstop.events;

import com.hawkeye.pitstop.config.WebSocketConfig;
import com.hawkeye.pitstop.entities.PitStopEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.hateoas.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(PitStopEntity.class)
public class EventHandler {
    private static final Logger log = LoggerFactory.getLogger(EventHandler.class);
    private static final String MESSAGE_PREFIX = "/topic";

    private final SimpMessagingTemplate websocket;

    private final EntityLinks entityLinks;

    @Autowired
    public EventHandler(SimpMessagingTemplate websocket, EntityLinks entityLinks) {
        this.websocket = websocket;
        this.entityLinks = entityLinks;
    }

//    @HandleAfterCreate
//    public void newPitStopEntity(PitStopEntity pitStopEntity) {
//        this.websocket.convertAndSend(
//                MESSAGE_PREFIX + "/newPitStopEntity", getPath(pitStopEntity));
//        log.info("new entity message sent");
//    }
//
//    @HandleAfterDelete
//    public void deletePitStopEntity(PitStopEntity pitStopEntity) {
//        this.websocket.convertAndSend(
//                MESSAGE_PREFIX + "/deletePitStopEntity", getPath(pitStopEntity));
//        log.info("delete entity message sent");
//    }

    @HandleAfterSave
    public void updatePitStopEntity(PitStopEntity pitStopEntity) {
        this.websocket.convertAndSend(
                WebSocketConfig.MESSAGE_PREFIX + "/updatePitStopEntity", getPath(pitStopEntity));
        log.info("update entity message sent");
    }

    /**
     * Take an {@link PitStopEntity} and get the URI using Spring Data REST's {@link EntityLinks}.
     *
     * @param pitStopEntity
     */
    private String getPath(PitStopEntity pitStopEntity) {
        return this.entityLinks.linkForSingleResource(pitStopEntity.getClass(),
                pitStopEntity.getId()).toUri().getPath();
    }

}