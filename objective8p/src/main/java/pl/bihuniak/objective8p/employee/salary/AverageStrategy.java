package pl.bihuniak.objective8p.employee.salary;

import java.util.List;

public class AverageStrategy implements SalaryStrategy{

    @Override
    public int calculate(List<Integer> salaries) {
        int sum = 0;
        for (Integer salary : salaries){
            sum += salary;
        }
        if(salaries.size() == 0){
            return 0;
        }
        return sum / salaries.size();
    }
}
