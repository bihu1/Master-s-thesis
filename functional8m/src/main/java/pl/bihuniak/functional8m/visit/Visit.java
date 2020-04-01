package pl.bihuniak.functional8m.visit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Visit {
    @Id
    private Integer id;
    private LocalDate date;
    private List<Integer> diseases;
    private List<Integer> medicalServices;
}