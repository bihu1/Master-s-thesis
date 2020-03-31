package pl.bihuniak.objective8p.employee.salary;

import java.util.List;

public class MaxStrategy implements SalaryStrategy{
    @Override
    public int calculate(List<Integer> salaries) {
        int max = 0;
        for(Integer salary : salaries){
            if(salary > max){
                max = salary;
            }
        }
        return max;
    }
}