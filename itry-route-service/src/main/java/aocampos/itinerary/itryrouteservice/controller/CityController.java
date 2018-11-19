package aocampos.itinerary.itryrouteservice.controller;

import aocampos.itinerary.itryrouteservice.controller.dto.CityDTO;
import aocampos.itinerary.itryrouteservice.model.City;
import aocampos.itinerary.itryrouteservice.service.CityService;
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

import java.util.List;

@Api(value = "City", description = "EndPoints to handle Cities")
@Slf4j
@RestController
@RequestMapping(value = "/cities")

public class CityController {

    private CityService cityService;
    private ObjectMapper mapper;

    @Autowired
    public CityController(CityService cityService,
                          ObjectMapper mapper) {
        this.cityService = cityService;
        this.mapper = mapper;
    }

    @ApiOperation(value = "List all Cities")
    @GetMapping
    public ResponseEntity<List<City>> listAllCities() {
        log.info("Retrieving all Cities");

        List<City> allCities = cityService.listAllCities();

        if (allCities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(allCities, HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve a City by id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCity(@PathVariable Long id) {
        log.info("Retrieving City {}", id);

        City city = cityService.getCity(id);

        if (city == null) {
            return new ResponseEntity<>("City id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @ApiOperation(value = "Create a City")
    @PostMapping
    public ResponseEntity<?> createCity(@RequestBody CityDTO cityDTO, UriComponentsBuilder ucBuilder) {
        log.info("Creating City {}", cityDTO.getName());

        City city = mapper.convertValue(cityDTO, City.class);
        city = cityService.createCity(city);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/cities/{id}").buildAndExpand(city.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a City")
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateCity(@PathVariable Long id, @RequestBody CityDTO cityDTO) {
        log.info("Updating City {}", id);

        City city = cityService.updateCity(id, cityDTO);

        if (city == null) {
            return new ResponseEntity<>("City id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a City")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable Long id) {
        log.info("Deleting the City {}", id);

        City city = cityService.getCity(id);

        if (city == null) {
            log.info("Unable to delete. City id {} not found", id);
            return new ResponseEntity<>("City id " + id + " not found", HttpStatus.NOT_FOUND);
        }

        cityService.deleteCity(id);
        return new ResponseEntity<City>(HttpStatus.NO_CONTENT);
    }

}
