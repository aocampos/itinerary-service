package aocampos.itinerary.itryrouteservice.repository;

import aocampos.itinerary.itryrouteservice.model.Travel;
import org.springframework.data.repository.CrudRepository;

public interface TravelRepository extends CrudRepository<Travel, Long> {
}
