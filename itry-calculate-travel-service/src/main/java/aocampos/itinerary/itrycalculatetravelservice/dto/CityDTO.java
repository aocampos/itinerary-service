package aocampos.itinerary.itrycalculatetravelservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CityDTO {

    private Long id;

    private String name;

    private Set<TravelDTO> travel;
}

