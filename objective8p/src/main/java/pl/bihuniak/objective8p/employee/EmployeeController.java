package pl.bihuniak.objective8p.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.bihuniak.objective8p.employee.salary.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final Map<String, String> codeToDistrict;

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

    @GetMapping("/employees/men/adults")
    public List<EmployeeSummary> getGroupedByCity(@RequestParam String city){
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

    @GetMapping("/employees/city/grouped")
    public Map<String, List<EmployeeSummary>> getPartitionedByCity(){
        Map<String, List<EmployeeSummary>> map = new HashMap<>();
        for(Employee employee : employeeRepository.findAll()){
            String city = employee.getAddress().getCity();
            if(!map.containsKey(city)){
                map.put(city, new ArrayList<>());
            }
            map.get(city).add(new EmployeeSummary(employee));
        }
        return map;
    }

    @GetMapping("/employees/city/partition")
    public Map<Boolean, List<EmployeeSummary>> getAdultsMenFromWroclawDetails3(@RequestParam String city){
        Map<Boolean, List<EmployeeSummary>> map = new HashMap<>();
        map.put(true, new ArrayList<>());
        map.put(false, new ArrayList<>());

        for(Employee employee : employeeRepository.findAll()){
            EmployeeSummary employeeSummary = new EmployeeSummary(employee);
            if(employee.getAddress().getCity().equals(city)){
                map.get(true).add(employeeSummary);
                continue;
            }
            map.get(false).add(employeeSummary);
        }
        return map;
    }

    @GetMapping("/employees/{employeeId}/district")
    public String getEmployeeCityDistrict(@PathVariable Integer employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        String district = null;
        if(employee != null && employee.getAddress().getCity().equals("New York City")){
            district = codeToDistrict.get(employee.getAddress().getZipCode());
        }
        if(district != null){
            return district;
        }
        return "Not Supported District";
    }

    @GetMapping("/employees/salaries")
    public int getEmployeesSalaries(@RequestParam SalaryStrategyType strategyType){
        SalaryStrategy salaryStrategy;
        switch (strategyType) {
            case ANY:
                salaryStrategy = new AnyStrategy();
                break;
            case MAX:
                salaryStrategy = new MaxStrategy();
                break;
            case MIN:
                salaryStrategy = new MinStrategy();
                break;
            case SUM:
                salaryStrategy = new SumStrategy();
                break;
            case AVERAGE:
                salaryStrategy = new AverageStrategy();
                break;
            default:
                throw new RuntimeException("No strategy fit!");
        }
        List<Integer> salaries = new ArrayList<>();
        for(Employee employee : employeeRepository.findAll()){
            salaries.add(employee.getSalary());
        }
        return salaryStrategy.calculate(salaries);
    }
}