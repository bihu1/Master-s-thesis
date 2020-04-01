package pl.bihuniak.functional8m.visit;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VisitRepository extends CrudRepository<Visit, Integer> {
    List<Visit> findAll();
}