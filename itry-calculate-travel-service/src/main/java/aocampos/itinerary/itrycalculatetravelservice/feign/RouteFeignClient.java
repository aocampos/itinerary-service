package aocampos.itinerary.itrycalculatetravelservice.feign;

import aocampos.itinerary.itrycalculatetravelservice.dto.CityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${rest.url.route-service}")
public interface RouteFeignClient {

    @GetMapping(value = "/routes/{originCityId}/{destinyCityId}/shortest-path")
    Iterable<CityDTO> getItineraryShortestPath(@PathVariable("originCityId") Long originCityId,
                                               @PathVariable("destinyCityId") Long destinyCityId);

    @GetMapping(value = "/routes/{originCityId}/{destinyCityId}")
    Iterable<CityDTO> getRoutes(@PathVariable("originCityId") Long originCityId,
                                @PathVariable("destinyCityId") Long destinyCityId);

}
