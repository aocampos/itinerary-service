package aocampos.itinerary.itrycalculatetravelservice.service;

import aocampos.itinerary.itrycalculatetravelservice.dto.CityDTO;
import aocampos.itinerary.itrycalculatetravelservice.dto.ItineraryCityDTO;
import aocampos.itinerary.itrycalculatetravelservice.dto.ItineraryDTO;
import aocampos.itinerary.itrycalculatetravelservice.dto.TravelDTO;
import aocampos.itinerary.itrycalculatetravelservice.feign.RouteFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class ItineraryService {

    private RouteFeignClient routeFeignClient;

    @Autowired
    public ItineraryService(RouteFeignClient routeFeignClient) {
        this.routeFeignClient = routeFeignClient;
    }

    public List<ItineraryDTO> getItineraries(Long originCityId, Long destinyCityId) {
        ItineraryDTO itineraryShortestPath = getItineraryShortestPath(originCityId, destinyCityId);

        ItineraryDTO itineraryShortestTime = getItineraryShortestTime(originCityId, destinyCityId);

        List<ItineraryDTO> itinerariesDTOS = new ArrayList<>();

        itinerariesDTOS.add(itineraryShortestPath);
        itinerariesDTOS.add(itineraryShortestTime);

        return itinerariesDTOS;
    }

    private ItineraryDTO getItineraryShortestPath(Long originCityId, Long destinyCityId) {

        ItineraryDTO itineraryShortestPathDTO = new ItineraryDTO();
        itineraryShortestPathDTO.setType("LESS_CONNECTION");

        Iterable<CityDTO> routes = routeFeignClient.getItineraryShortestPath(originCityId, destinyCityId);

        List<ItineraryCityDTO> itineraryCityDTOS = createItinerary(routes);

        itineraryShortestPathDTO.setCities(itineraryCityDTOS);
        return itineraryShortestPathDTO;
    }

    private ItineraryDTO getItineraryShortestTime(Long originCityId, Long destinyCityId) {

        ItineraryDTO itineraryShortestTimeDTO = new ItineraryDTO();
        itineraryShortestTimeDTO.setType("LESS_TIME");

        Iterable<CityDTO> routes = routeFeignClient.getRoutes(originCityId, destinyCityId);

        List<ItineraryCityDTO> itineraryCityDTOS = createItinerary(routes);

        itineraryShortestTimeDTO.setCities(itineraryCityDTOS);
        return itineraryShortestTimeDTO;
    }

    private List<ItineraryCityDTO> createItinerary(Iterable<CityDTO> routes) {
        List<ItineraryCityDTO> itineraryCityDTOS = new ArrayList<>();

        routes.forEach(cityDTO -> {
            int currentSizeItineraryCity = itineraryCityDTOS.size();
            currentSizeItineraryCity += 1;

            Set<TravelDTO> travel = cityDTO.getTravel();
            if (travel.iterator().hasNext()) {

                ItineraryCityDTO itineraryCityDTO = new ItineraryCityDTO();
                itineraryCityDTO.setOriginCityId(cityDTO.getId());
                itineraryCityDTO.setOriginCityName(cityDTO.getName());
                itineraryCityDTO.setSequence(currentSizeItineraryCity);

                TravelDTO nextTravel = travel.iterator().next();
                LocalTime departureTime = LocalTime.parse(nextTravel.getDepartureTime());
                LocalTime arrivalTime = LocalTime.parse(nextTravel.getArrivalTime());

                itineraryCityDTO.setDepartureTime(departureTime);
                itineraryCityDTO.setArrivalTime(arrivalTime);

                Set<CityDTO> city = nextTravel.getCity();
                CityDTO nextCity = city.iterator().next();

                itineraryCityDTO.setDestinyCityId(nextCity.getId());
                itineraryCityDTO.setDestinyCityName(nextCity.getName());

                itineraryCityDTOS.add(itineraryCityDTO);
            }
        });
        return itineraryCityDTOS;
    }
}
