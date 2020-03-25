package pl.bihuniak.reactive8mr;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

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

    @GetMapping("/employees/test")
    public Flux<EmployeeSummary> getAdultsMenFromWroclawDetails(@RequestParam String city){
        return employeeRepository.findAll()
            .filter(e -> e.getGender().equals(Gender.MALE) &&
                         e.getBirthDay().isBefore(LocalDate.now().minusYears(18)) &&
                         e.getAddress().getCity().equalsIgnoreCase(city))
            .map(EmployeeSummary::new);
    }
}