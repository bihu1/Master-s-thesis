package pl.bihuniak.reactive8pr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
public class Reactive8prApplication {

    public static void main(String[] args) {
        SpringApplication.run(Reactive8prApplication.class, args);
    }
}