package pl.bihuniak.reactive8pr.visit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visit {
    @Id
    private Integer id;
    private LocalDate date;
}