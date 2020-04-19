package pl.bihuniak.reactive8mr.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("employees")
public class Employee {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private Address address;
    private int socialSecurity;
    private boolean active;
    private Gender gender;
}