package pl.bihuniak.reactive8pr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableR2dbcRepositories
@RestController
public class Reactive8prApplication {

    @Autowired
    private TestRepository testRepository;

    public static void main(String[] args) {
        SpringApplication.run(Reactive8prApplication.class, args);
        System.out.println("Finiszhed");
        System.out.println("Finiszhed");
    }

    @GetMapping("/a")
    public Mono<Test> test(){
       return Mono.just(new Test(null, "maKota"))
           .flatMap(testRepository::save);
    }
}