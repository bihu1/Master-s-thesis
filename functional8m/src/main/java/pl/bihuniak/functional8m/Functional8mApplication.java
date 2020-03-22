package pl.bihuniak.functional8m;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Functional8mApplication {

    @Autowired
    private TestRepository testRepository;

    public static void main(String[] args) {
        SpringApplication.run(Functional8mApplication.class, args);
    }

    @PostConstruct
    public void test(){
        testRepository.save(new Test());
    }
}