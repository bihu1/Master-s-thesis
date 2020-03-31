package pl.bihuniak.functional8p.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.bihuniak.functional8p.employee.salary.SalaryStrategyType;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final Map<String, String> codeToDistrict;

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

    @GetMapping("/employees/men/adults")
    public List<EmployeeSummary> getGroupedByCity(@RequestParam String city){
        return employeeRepository.findAll().stream()
            .filter(e -> e.getGender().equals(Gender.MALE))
            .filter(e -> e.getBirthDay().isBefore(LocalDate.now().minusYears(18)))
            .filter(e -> e.getAddress().getCity().equalsIgnoreCase(city))
            .map(EmployeeSummary::new)
            .collect(toList());
    }

    @GetMapping("/employees/city/grouped")
    public Map<String, List<EmployeeSummary>> getGroupedByCity(){
        return employeeRepository.findAll().stream()
            .collect(groupingBy(x -> x.getAddress().getCity(), mapping(EmployeeSummary::new, toList())));
    }

    @GetMapping("/employees/city/partition")
    public Map<Boolean, List<EmployeeSummary>> getPartitionedByCity(@RequestParam String city){
        return employeeRepository.findAll().stream()
            .collect(partitioningBy(x -> x.getAddress().getCity().equals(city), mapping(EmployeeSummary::new, toList())));
    }

    @GetMapping("/employees/{employeeId}/district")
    public String getEmployeeCityDistrict(@PathVariable Integer employeeId){
        return employeeRepository.findById(employeeId)
            .map(Employee::getAddress)
            .filter(x -> x.getCity().equals("New York City"))
            .map(Address::getZipCode)
            .flatMap(x -> ofNullable(codeToDistrict.get(x)))
            .orElse("Not Supported District");
    }

    @GetMapping("/employees/salaries")
    public int getEmployeesSalaries(@RequestParam SalaryStrategyType strategyType){
        Stream<Integer> salaries = employeeRepository.findAll().stream()
            .map(Employee::getSalary);
        return strategyType.calculate(salaries);
    }
}