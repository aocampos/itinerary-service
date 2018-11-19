package aocampos.itinerary.itrycalculatetravelservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class TravelDTO {

    private Long id;

    private String code;

    private String departureTime;

    private String arrivalTime;

    private Long duration;

    private Set<CityDTO> city;
}
