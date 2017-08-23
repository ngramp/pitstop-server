package com.hawkeye.pitstop;

import com.hawkeye.pitstop.entities.PitStopEntity;
import com.hawkeye.pitstop.model.LiveFeed;
import com.hawkeye.pitstop.model.PitStop;
import com.hawkeye.pitstop.model.Vehicle;
import com.hawkeye.pitstop.services.PitStopService;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LiveFeedSync extends Thread{
    private String server;
    private static final String mapping = "/api/livefeed";
    private PitStopService service;
    public LiveFeedSync(String addr, String port, PitStopService service){
        server = "http://"+addr+":"+port+mapping;
        this.service = service;
    }
    public void run(){
        int oldPitStopCount = 0;
        try{
            //TODO: not ideal, what if server goes down then comes back up?
            System.out.println("Waiting for server");
            while(!checkServerUp()){
                sleep(1000);
            }
            System.out.println("Server now up");
            while(checkServerUp()){
                RestTemplate restTemplate = new RestTemplate();
                LiveFeed feed = restTemplate.getForObject(
                        server, LiveFeed.class);
                List<PitStop> pitStops = new ArrayList<>();
                for(Vehicle vehicle : feed.getVehicles()){
                    pitStops.addAll(vehicle.getPitStops());
                }
                if(pitStops.size() > oldPitStopCount){
                    oldPitStopCount = pitStops.size();
                    //TODO: relying on service to store or update, could find the new ones
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
        catch (IOException e){
            System.out.println("IOException");
        }
        catch(InterruptedException e){
            System.out.println("InterruptedException");
        }
    }
    private boolean checkServerUp() throws IOException{
        boolean isAlive = true;
        URL url = new URL(server);
        HttpURLConnection httpConn =  (HttpURLConnection)url.openConnection();
        httpConn.setInstanceFollowRedirects( false );
        httpConn.setRequestMethod( "HEAD" );
        //TODO: try/catch is not really for business logic
        try{
            httpConn.connect();
        }
        catch(Exception e){
            isAlive = false;
        }
        return isAlive;
    }

}
