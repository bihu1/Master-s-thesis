package pl.bihuniak.reactive8mr.visit;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface MedicalServiceRepository extends ReactiveCrudRepository<MedicalService, Integer> {
    Flux<MedicalService> findByIdIn(List<Integer> ids);
}
