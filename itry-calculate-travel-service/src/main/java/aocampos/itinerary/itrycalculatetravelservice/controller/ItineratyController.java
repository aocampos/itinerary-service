package aocampos.itinerary.itrycalculatetravelservice.controller;

import aocampos.itinerary.itrycalculatetravelservice.dto.ItineraryDTO;
import aocampos.itinerary.itrycalculatetravelservice.service.ItineraryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(description = "EndPoints to handle Itineraries")
@Slf4j
@RestController
@RequestMapping(value = "/itinerary")
public class ItineratyController {

    private ItineraryService itineraryService;

    @Autowired
    public ItineratyController(ItineraryService itineraryService) {
        this.itineraryService = itineraryService;
    }

    @ApiOperation(value = "Retrieve itineraries based between two cites")
    @GetMapping(value = "/{originCityId}/{destinyCityId}")
    public ResponseEntity<List<ItineraryDTO>> getItinerary(@PathVariable("originCityId") Long originCityId,
                                                           @PathVariable("destinyCityId") Long destinyCityId) {
        log.info("Getting the Itinerary between {} and {}", originCityId, destinyCityId);

        List<ItineraryDTO> itineraries = itineraryService.getItineraries(originCityId, destinyCityId);

        return new ResponseEntity<>(itineraries, HttpStatus.OK);
    }
}

