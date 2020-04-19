package pl.bihuniak.functional8m.employee;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, String> {
    List<Employee> findAll();
}
