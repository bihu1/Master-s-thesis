package pl.bihuniak.reactive8pr.visit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalService {
    @Id
    private Integer id;
    private String name;
}
