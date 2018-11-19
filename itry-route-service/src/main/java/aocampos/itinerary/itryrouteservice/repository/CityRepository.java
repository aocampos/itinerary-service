package aocampos.itinerary.itryrouteservice.repository;

import aocampos.itinerary.itryrouteservice.model.City;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CityRepository extends CrudRepository<City, Long> {

    @Query("MATCH path = shortestPath((a:CityDTO)-[:HAS_TRAVEL|:TRAVELING_TO*]->(b:CityDTO)) " +
            "WHERE ID(a) = {originCityId} AND ID(b) = {destinyCityId} " +
            "RETURN path")
    Iterable<City> getShortestPath(@Param("originCityId") Long originCityId,
                                   @Param("destinyCityId") Long destinyCityId);

    @Query("MATCH path = (a:CityDTO)-[:HAS_TRAVEL|:TRAVELING_TO*]->(z:CityDTO) " +
            "WHERE ID(a) = {originCityId} AND ID(z) = {destinyCityId} " +
            "return path")
    Iterable<City> getRoutes(@Param("originCityId") Long originCityId,
                             @Param("destinyCityId") Long destinyCityId);
}
