package pl.bihuniak.objective7p;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

@SpringBootApplication
public class Objective7pApplication {

    @Autowired
    private TestRepository testRepository;

    public static void main(String[] args) {
        SpringApplication.run(Objective7pApplication.class, args);
    }

    @PostConstruct
    public void test(){
        testRepository.save(new Test());
    }
}
