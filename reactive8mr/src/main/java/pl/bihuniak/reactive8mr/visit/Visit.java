package pl.bihuniak.reactive8mr.visit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visit {
    @Id
    private Integer id;
    private LocalDate date;
    private List<Integer> diseases;
    private List<Integer> medicalServices;

    public Visit(LocalDate date) {
        this.date = date;
    }
}