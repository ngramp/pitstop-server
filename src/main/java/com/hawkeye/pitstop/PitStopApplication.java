package com.hawkeye.pitstop;

import com.hawkeye.pitstop.services.PitStopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.hawkeye.pitstop")
public class PitStopApplication {
    private static final Logger log = LoggerFactory.getLogger(PitStopApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(PitStopApplication.class, args);
	}
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(PitStopService service) throws Exception {
        return args -> {
            //TODO: addr:port should not be hard coded, pass in args[]
            LiveFeedSync p = new LiveFeedSync("localhost", "9000", service);
            p.start();
        };
    }
}
