package aocampos.itinerary.itryrouteservice.controller.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class TravelUpdateDTO {

    @ApiModelProperty(dataType = "java.lang.String", example = "12:00")
    private LocalTime departureTime;

    @ApiModelProperty(dataType = "java.lang.String", example = "12:00")
    private LocalTime arrivalTime;

    private int duration;
}
