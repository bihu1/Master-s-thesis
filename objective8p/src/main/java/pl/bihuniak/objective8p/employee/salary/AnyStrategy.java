package pl.bihuniak.objective8p.employee.salary;

import java.util.List;
import java.util.Random;

public class AnyStrategy implements SalaryStrategy{
    @Override
    public int calculate(List<Integer> salaries) {
        if(salaries.size() == 0){
            throw new RuntimeException("No salaries");
        }
        Random r = new Random();
        return salaries.get(r.nextInt(salaries.size()));
    }
}