package pl.bihuniak.reactive8mr;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

//@SpringBootTest
class Reactive8mrApplicationTests {

    private static String s = "";

    @Test
    void contextLoads() {
        Flux.just(1,2,3,4,5,0)
            .map(x -> 10 / x)
            .doOnNext(Reactive8mrApplicationTests::print)
            .map(x -> 5 / x)
            .doOnNext(Reactive8mrApplicationTests::print)
            .subscribe();
    }

    private static Object print(Object o) {
        s = !s.isEmpty() ? s.concat("->") : s;
        s = s.concat(o.toString());
        System.out.println(s);
        return o;
    }
}
