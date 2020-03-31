package pl.bihuniak.objective8p.employee;

import org.springframework.data.repository.CrudRepository;


public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
