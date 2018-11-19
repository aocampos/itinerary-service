package aocampos.itinerary.itrycalculatetravelservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class ItineraryCityDTO {

    private int sequence;

    private Long originCityId;
    private String originCityName;

    private Long destinyCityId;
    private String destinyCityName;

    private LocalTime departureTime;
    private LocalTime arrivalTime;
}
