package pl.bihuniak.objective8p;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping("/employees/{employeeId}")
    public Employee findEmployeeById(@PathVariable Integer employeeId){
        return employeeRepository.findById(employeeId).orElse(null);
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
        List<EmployeeSummary> employeeSummaries = new ArrayList<>();
        for (Employee employee : employeeRepository.findAll()){
            if(!employee.getGender().equals(Gender.MALE)) continue;
            if(employee.getBirthDay().isAfter(LocalDate.now().minusYears(18))) continue;
            if(!employee.getAddress().getCity().equalsIgnoreCase(city)) continue;
            EmployeeSummary employeeSummary = new EmployeeSummary(employee);
            employeeSummaries.add(employeeSummary);
        }
        return employeeSummaries;
    }
}