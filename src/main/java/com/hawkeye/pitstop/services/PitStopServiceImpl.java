package com.hawkeye.pitstop.services;

import com.hawkeye.pitstop.repositories.PitStopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class PitStopServiceImpl implements PitStopService{
    private static final Logger log = LoggerFactory.getLogger(PitStopServiceImpl.class);

    private PitStopRepository pitStopRepository;

    @Autowired
    public PitStopServiceImpl(PitStopRepository pitStopRepository){
        this.pitStopRepository = pitStopRepository;
    }
}
