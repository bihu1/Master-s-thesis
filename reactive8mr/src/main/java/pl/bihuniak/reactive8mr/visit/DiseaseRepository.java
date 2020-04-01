package pl.bihuniak.reactive8mr.visit;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface DiseaseRepository extends ReactiveCrudRepository<Disease, Integer> {
    Flux<Disease> findByIdIn(List<Integer> ids);
}
