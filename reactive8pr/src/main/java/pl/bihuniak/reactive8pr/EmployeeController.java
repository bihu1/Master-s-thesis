package pl.bihuniak.reactive8pr;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping("/employees/{employeeId}")
    public Mono<Employee> findEmployeeById(@PathVariable Integer employeeId){
        return Mono.just(employeeId)
            .flatMap(employeeRepository::findById);
    }

    @GetMapping("/employees")
    public Flux<Employee> findEmployees(){
        return employeeRepository.findAll();
    }

    @PostMapping("/employees")
    public Mono<Employee> saveOrUpdateEmployee(@RequestBody Mono<Employee> employee){
        return employee
            .flatMap(employeeRepository::save);
    }

    @DeleteMapping("/employees/{employeeId}")
    public Mono<Void> deleteEmployeeById(@PathVariable Integer employeeId){
        return Mono.just(employeeId)
            .flatMap(employeeRepository::deleteById);
    }
}