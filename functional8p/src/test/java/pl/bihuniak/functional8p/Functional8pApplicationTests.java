package pl.bihuniak.functional8p;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
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

    @Test
    void aaaaa(){
        Stream.of(new ATest(null, null, null), new ATest("a",null,null),new ATest("a","b",null), new ATest("a","b","c1"), new ATest("a","b","c2"), new ATest(null,"b","c1"), new ATest(null,"b","c2"))
            .sorted(getComparator())
            .forEach(System.out::println);
    }

    public Comparator<ATest> getComparator() {
        return Comparator.comparing((ATest p) -> p.a, Comparator.nullsLast(Comparator.naturalOrder()))
            .thenComparing((ATest p) -> p.b, Comparator.nullsLast(Comparator.naturalOrder()))
            .thenComparing((ATest p) -> p.c, Comparator.nullsLast(Comparator.naturalOrder()));
    }
}
