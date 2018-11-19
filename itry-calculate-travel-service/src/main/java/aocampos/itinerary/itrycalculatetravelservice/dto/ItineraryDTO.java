package aocampos.itinerary.itrycalculatetravelservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItineraryDTO {

    private String type;
    private List<ItineraryCityDTO> cities;

}
