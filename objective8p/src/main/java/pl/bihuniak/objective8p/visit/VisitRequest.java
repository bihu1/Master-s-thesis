package pl.bihuniak.objective8p.visit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitRequest {
    private LocalDate date;
    private List<Integer> services;
    private List<Integer> diseases;
}