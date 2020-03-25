package pl.bihuniak.functional8p;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "employees")
public class Employee {
    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    @Embedded
    private Address address;
    private int socialSecurity;
    private boolean active;
    @Enumerated(EnumType.STRING)
    private Gender gender;
}