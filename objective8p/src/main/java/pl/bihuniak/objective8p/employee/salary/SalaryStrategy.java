package pl.bihuniak.objective8p.employee.salary;

import java.util.List;

public interface SalaryStrategy {

    int calculate(List<Integer> salaries);
}