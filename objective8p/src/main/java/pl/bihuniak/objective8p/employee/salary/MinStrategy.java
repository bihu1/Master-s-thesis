package pl.bihuniak.objective8p.employee.salary;

import java.util.List;

public class MinStrategy implements SalaryStrategy{
    @Override
    public int calculate(List<Integer> salaries) {
        int min = 0;
        for(Integer salary : salaries){
            if(salary < min){
                min = salary;
            }
        }
        return min;
    }
}
