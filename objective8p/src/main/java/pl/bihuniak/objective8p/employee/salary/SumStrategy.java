package pl.bihuniak.objective8p.employee.salary;

import java.util.List;

public class SumStrategy implements SalaryStrategy {
    @Override
    public int calculate(List<Integer> salaries) {
        int sum = 0;
        for(Integer salary : salaries){
            sum += salary;
        }
        return sum;
    }
}
