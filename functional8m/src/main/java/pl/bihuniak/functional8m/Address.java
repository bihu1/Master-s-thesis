package pl.bihuniak.functional8m;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String houseNumber;
    private String street;
    private String city;
    private String zipCode;
}