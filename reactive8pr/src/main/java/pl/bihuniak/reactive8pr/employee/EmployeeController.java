package pl.bihuniak.reactive8pr.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

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

    @DeleteMapping("/employees")
    public Mono<Void> deleteAll(){
        return employeeRepository.deleteAll();
    }

    @GetMapping("/employees/test")
    public Flux<EmployeeSummary> getAdultsMenFromWroclawDetails(@RequestParam String city){
        return employeeRepository.findAll()
            .filter(e -> e.getGender().equals(Gender.MALE) &&
                e.getBirthDay().isBefore(LocalDate.now().minusYears(18)) &&
                e.getCity().equalsIgnoreCase(city))
            .map(EmployeeSummary::new);
    }

    @GetMapping("/employees/city/grouped")
    public Mono<Map<String, List<EmployeeSummary>>> getPartitionedByCity(){
        return employeeRepository.findAll()
            .collect(groupingBy(Employee::getCity, mapping(EmployeeSummary::new, toList())));
    }

    @GetMapping("/employees/city/partition")
    public Mono<Map<Boolean, List<EmployeeSummary>>> getAdultsMenFromWroclawDetails3(@RequestParam String city){
        return employeeRepository.findAll()
            .collect(partitioningBy(x -> x.getCity().equals(city), mapping(EmployeeSummary::new, toList())));
    }
}