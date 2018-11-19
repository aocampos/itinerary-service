package aocampos.itinerary.itryrouteservice.service;

import aocampos.itinerary.itryrouteservice.model.City;
import aocampos.itinerary.itryrouteservice.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.StreamSupport;

@Slf4j
@Service
public class RouteService {

    private CityRepository cityRepository;

    @Autowired
    public RouteService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    /**
     * Get the shortest path between two Cities. The query uses the function shortestPath of Cypher.
     *
     * @param originCityId
     * @param destinyCityId
     * @return
     */
    public Iterable<City> getShortestPath(Long originCityId, Long destinyCityId) {
        Iterable<City> shortestPath = cityRepository.getShortestPath(originCityId, destinyCityId);

        long quantityCity = StreamSupport.stream(shortestPath.spliterator(), false).count();

        log.info("Retrieved {} Cities", quantityCity);

        return shortestPath;
    }

    /**
     * Get all the routes between two Cities.
     *
     * @param originCityId
     * @param destinyCityId
     * @return
     */
    public Iterable<City> getRoutes(Long originCityId, Long destinyCityId) {
        Iterable<City> routes = cityRepository.getRoutes(originCityId, destinyCityId);

        long quantityCity = StreamSupport.stream(routes.spliterator(), false).count();

        log.info("Retrieved {} Cities", quantityCity);

        return routes;
    }
}
