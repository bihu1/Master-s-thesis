package pl.bihuniak.functional8p;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;
import java.util.stream.Stream;

//@SpringBootTest
class Functional8pApplicationTests {

    @Test
    void contextLoads() {
        Stream.of(1,2,3,4,5,0)
            .map(x -> 10 / x)
            .peek(System.out::println)
            .map(x -> 5 / x)
            .peek(System.out::println)
            .collect(Collectors.toList());
    }

}
