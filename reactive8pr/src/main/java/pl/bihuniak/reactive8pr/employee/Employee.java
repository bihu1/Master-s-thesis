package pl.bihuniak.reactive8pr.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("employees")
public class Employee {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private String houseNumber;
    private String street;
    private String city;
    private String zipCode;
    private int socialSecurity;
    private boolean active;
    private Gender gender;
}