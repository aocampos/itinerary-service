package aocampos.itinerary.itryrouteservice.controller;

import aocampos.itinerary.itryrouteservice.model.City;
import aocampos.itinerary.itryrouteservice.service.RouteService;
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

@Api(value = "Route", description = "EndPoints to handle Routes")
@Slf4j
@RestController
@RequestMapping(value = "/routes")
public class RouteController {

    private RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @ApiOperation(value = "Retrieve a shortestPath between to Cities")
    @GetMapping(value = "/{originCityId}/{destinyCityId}/shortest-path")
    public ResponseEntity<Iterable<City>> getShortestPath(@PathVariable Long originCityId,
                                                          @PathVariable Long destinyCityId) {
        log.info("Retrieving the shortest path between CityId {} and CityId {}", originCityId, destinyCityId);

        Iterable<City> shortestPath = routeService.getShortestPath(originCityId, destinyCityId);

        return new ResponseEntity(shortestPath, HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve a shortestPath between to Cities")
    @GetMapping(value = "/{originCityId}/{destinyCityId}")
    public ResponseEntity<Iterable<City>> getRoute(@PathVariable Long originCityId,
                                                   @PathVariable Long destinyCityId) {
        log.info("Retrieving the routes between CityId {} and CityId {}", originCityId, destinyCityId);

        Iterable<City> shortestPath = routeService.getRoutes(originCityId, destinyCityId);

        return new ResponseEntity(shortestPath, HttpStatus.OK);
    }
}
