package com.hawkeye.pitstop;

import com.hawkeye.pitstop.entities.PitStopEntity;
import com.hawkeye.pitstop.model.LiveFeed;
import com.hawkeye.pitstop.model.PitStop;
import com.hawkeye.pitstop.model.Vehicle;
import com.hawkeye.pitstop.services.PitStopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LiveFeedSync extends Thread{
    private static final Logger log = LoggerFactory.getLogger(LiveFeedSync.class);
    private String server;
    private static final String mapping = "/api/livefeed";
    private PitStopService service;
    public LiveFeedSync(String addr, String port, PitStopService service){
        this.server = "http://"+addr+":"+port+mapping;
        this.service = service;
    }
    public void run(){
        int oldPitStopCount = 0;
        try{
            //TODO: not ideal, what if server goes down then comes back up?
            log.info("Waiting for server");
            while(!checkServerUp()){
                sleep(3000);
            }
            log.info("Server now up");
            while(checkServerUp()){
                RestTemplate restTemplate = new RestTemplate();
                LiveFeed feed;
                try{
                    feed = restTemplate.getForObject(
                            this.server, LiveFeed.class);
                }
                catch (RestClientException e){
                    log.warn("There was an error getting data from the api");
                    continue;
                }

                List<PitStop> pitStops = new ArrayList<>();
                for(Vehicle vehicle : feed.getVehicles()){
                    pitStops.addAll(vehicle.getPitStops());
                }
                if(pitStops.size() > oldPitStopCount){
                    oldPitStopCount = pitStops.size();
                    List<PitStopEntity> entities = new ArrayList<>();
                    for(Vehicle vehicle : feed.getVehicles()){
                        for(PitStop pitStop : vehicle.getPitStops()){
                            PitStopEntity ent = new PitStopEntity();
                            ent.setVehicleNumber(vehicle.getVehicleNumber());
                            ent.setTimeIn(pitStop.getTimeIn());
                            ent.setTimeOut(pitStop.getTimeOut());
                            entities.add(ent);
                        }
                    }
                    service.createOrUpdate(entities);
                }
                sleep(1000);
            }

        }
        catch(InterruptedException e){
            log.error("Server sync thread interrupted");
        }
    }
    private boolean checkServerUp() {
        boolean isAlive = true;
        try {
            URL url = new URL(server);
            HttpURLConnection httpConn =  (HttpURLConnection)url.openConnection();
            httpConn.setInstanceFollowRedirects( false );
            httpConn.setRequestMethod( "HEAD" );
            try{
                httpConn.connect();
            }
            catch(Exception e){
                isAlive = false;
            }
        }
        catch (Exception e){
            isAlive = false;
        }
        
        return isAlive;
    }

}
