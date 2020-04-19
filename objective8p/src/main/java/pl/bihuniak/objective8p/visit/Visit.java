package pl.bihuniak.objective8p.visit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Visit {
    @Id
    private Integer id;
    private LocalDate date;
    @ManyToMany
    private List<Disease> diseases;
    @ManyToMany
    private List<MedicalService> medicalServices;
}