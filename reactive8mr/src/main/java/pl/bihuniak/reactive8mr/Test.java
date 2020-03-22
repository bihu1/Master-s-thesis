package pl.bihuniak.reactive8mr;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Test {
    @Id
    private String id;
}
