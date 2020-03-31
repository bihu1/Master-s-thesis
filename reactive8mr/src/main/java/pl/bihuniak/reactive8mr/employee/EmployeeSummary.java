package pl.bihuniak.reactive8mr.employee;

import lombok.Data;

@Data
public class EmployeeSummary {
    private String firstName;
    private String lastName;
    private int socialSecurity;

    public EmployeeSummary(Employee employee) {
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.socialSecurity = employee.getSocialSecurity();
    }
}
