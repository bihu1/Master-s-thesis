package pl.bihuniak.reactive8mr;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TestRepository extends ReactiveCrudRepository<Test, String> {
}
