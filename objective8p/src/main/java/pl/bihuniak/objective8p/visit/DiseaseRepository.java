package pl.bihuniak.objective8p.visit;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiseaseRepository extends CrudRepository<Disease, Integer> {
    List<Disease> findByIdIn(List<Integer> ids);
}
