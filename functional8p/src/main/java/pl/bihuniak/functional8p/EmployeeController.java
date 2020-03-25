package pl.bihuniak.functional8p;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping("/employees/{employeeId}")
    public Optional<Employee> findEmployeeById(@PathVariable Integer employeeId){
        return employeeRepository.findById(employeeId);
    }

    @GetMapping("/employees")
    public Iterable<Employee> findEmployees(){
        return employeeRepository.findAll();
    }

    @PostMapping("/employees")
    public Employee saveOrUpdateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployeeById(@PathVariable Integer employeeId){
        employeeRepository.deleteById(employeeId);
    }
}