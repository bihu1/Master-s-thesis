package pl.bihuniak.functional8p.employee.salary;

import java.util.stream.Stream;

import static java.util.stream.Collectors.averagingInt;

@FunctionalInterface
public interface SalaryStrategy {

    int calculate(Stream<Integer> salaries);

    static SalaryStrategy anySalary(){
        return integerStream -> integerStream
            .findAny()
            .orElseThrow(() -> new RuntimeException("No salaries"));
    }

    static SalaryStrategy sumSalary(){
        return salaries -> salaries
            .mapToInt(x -> x)
            .sum();
    }

    static SalaryStrategy minSalary(){
        return salaries -> salaries
            .mapToInt(x -> x)
            .min()
            .orElse(0);
    }

    static SalaryStrategy maxSalary(){
        return salaries -> salaries
            .mapToInt(x -> x)
            .max()
            .orElse(0);
    }

    static SalaryStrategy averageSalary(){
        return intStream -> intStream
            .collect(averagingInt(num -> num))
            .intValue();

    }
}