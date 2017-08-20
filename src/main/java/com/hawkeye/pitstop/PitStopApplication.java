package com.hawkeye.pitstop;

import com.hawkeye.pitstop.model.LiveFeed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PitStopApplication {
    private static final Logger log = LoggerFactory.getLogger(PitStopApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PitStopApplication.class);
	}
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            LiveFeed feed = restTemplate.getForObject(
                    "http://localhost:9000/api/livefeed", LiveFeed.class);
            log.info(feed.toString());
        };
    }
}
