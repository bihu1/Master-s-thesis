package pl.bihuniak.functional8p;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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

    @GetMapping("/employees/test")
    public List<EmployeeSummary> getAdultsMenFromWroclawDetails(@RequestParam String city){
        return employeeRepository.findAll().stream()
            .filter(e -> e.getGender().equals(Gender.MALE))
            .filter(e -> e.getBirthDay().isBefore(LocalDate.now().minusYears(18)))
            .filter(e -> e.getAddress().getCity().equalsIgnoreCase(city))
            .map(EmployeeSummary::new)
            .collect(toList());
    }
}