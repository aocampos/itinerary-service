package aocampos.itinerary.itryrouteservice.controller;

import aocampos.itinerary.itryrouteservice.controller.dto.TravelDTO;
import aocampos.itinerary.itryrouteservice.controller.dto.TravelUpdateDTO;
import aocampos.itinerary.itryrouteservice.model.Travel;
import aocampos.itinerary.itryrouteservice.service.TravelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Api(value = "Travel", description = "EndPoints to handle Travels")
@Slf4j
@RestController
@RequestMapping("/travels")
public class TravelController {

    private TravelService travelService;
    private ObjectMapper mapper;

    @Autowired
    public TravelController(TravelService travelService,
                            ObjectMapper mapper) {
        this.travelService = travelService;
        this.mapper = mapper;
    }

    @ApiOperation(value = "Retrieve a Travel by id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getTravel(@PathVariable Long id) {
        log.info("Retrieving Travel {}", id);

        Travel travel = travelService.getTravel(id);

        if (travel == null) {
            log.info("Travel id {} not found", id);
            return new ResponseEntity<>("Travel id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(travel, HttpStatus.OK);
    }

    @ApiOperation(value = "Create a Travel")
    @PostMapping
    public ResponseEntity<?> createTravel(@RequestBody TravelDTO travelDTO, UriComponentsBuilder ucBuilder) {
        log.info("Creating Travel {}", travelDTO.getCode());

        Travel travel = travelService.createTravel(travelDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/travels/{id}").buildAndExpand(travel.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a Travel")
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateTravel(@PathVariable Long id, @RequestBody TravelUpdateDTO travelUpdateDTO) {
        log.info("Updating Travel {}", id);

        Travel travel = travelService.updateTravel(id, travelUpdateDTO);

        if (travel == null) {
            return new ResponseEntity<>("Travel id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(travel, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a Travel")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteTravel(@PathVariable Long id) {
        log.info("Deleting the Travel {}", id);

        Travel travel = travelService.getTravel(id);

        if (travel == null) {
            log.info("Unable to delete. Travel id {} not found", id);
            return new ResponseEntity<>("Travel id " + id + " not found", HttpStatus.NOT_FOUND);
        }

        travelService.deleteTravel(id);
        return new ResponseEntity<Travel>(HttpStatus.NO_CONTENT);
    }
}
