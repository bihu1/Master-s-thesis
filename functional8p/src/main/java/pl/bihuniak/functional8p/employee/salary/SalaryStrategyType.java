package pl.bihuniak.functional8p.employee.salary;

import java.util.stream.Stream;

public enum SalaryStrategyType {

    ANY(SalaryStrategy.anySalary()),
    SUM(SalaryStrategy.sumSalary()),
    MIN(SalaryStrategy.minSalary()),
    MAX(SalaryStrategy.maxSalary()),
    AVERAGE(SalaryStrategy.anySalary());

    private SalaryStrategy salaryStrategy;

    SalaryStrategyType(SalaryStrategy salaryStrategy){
        this.salaryStrategy = salaryStrategy;
    }

    public int calculate(Stream<Integer> salaries){
        return salaryStrategy.calculate(salaries);
    }
}