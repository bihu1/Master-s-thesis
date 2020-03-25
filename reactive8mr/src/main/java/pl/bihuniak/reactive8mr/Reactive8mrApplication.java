package pl.bihuniak.reactive8mr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Reactive8mrApplication {

    public static void main(String[] args) {
        SpringApplication.run(Reactive8mrApplication.class, args);
    }
}
