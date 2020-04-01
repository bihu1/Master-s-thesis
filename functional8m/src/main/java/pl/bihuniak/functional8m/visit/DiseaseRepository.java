package pl.bihuniak.functional8m.visit;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiseaseRepository extends CrudRepository<Disease, Integer> {
    List<Disease> findByIdIn(List<Integer> ids);
}
