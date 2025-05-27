package dk.sdu.cbse.common;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsumingRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumingRestApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            // Add points to the score
            restTemplate.getForObject("http://localhost:8080/addScore?point=10", Void.class);

            // Retrieve the current score
            int score = restTemplate.getForObject("http://localhost:8080/getScore", Integer.class);
            System.out.println(score);
        };
    }
}