package aocampos.itinerary.itryrouteservice.service;

import aocampos.itinerary.itryrouteservice.controller.dto.TravelDTO;
import aocampos.itinerary.itryrouteservice.controller.dto.TravelUpdateDTO;
import aocampos.itinerary.itryrouteservice.model.City;
import aocampos.itinerary.itryrouteservice.model.Travel;
import aocampos.itinerary.itryrouteservice.repository.CityRepository;
import aocampos.itinerary.itryrouteservice.repository.TravelRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.MINUTES;

@Slf4j
@Service
public class TravelService {

    private TravelRepository travelRepository;
    private CityRepository cityRepository;
    private ObjectMapper mapper;

    @Autowired
    public TravelService(TravelRepository travelRepository,
                         CityRepository cityRepository,
                         ObjectMapper mapper) {
        this.travelRepository = travelRepository;
        this.cityRepository = cityRepository;
        this.mapper = mapper;
    }

    public Travel createTravel(TravelDTO travelDTO) {

        Travel travel = mapper.convertValue(travelDTO, Travel.class);

        Long travelDuration = calculateTravelDuration(travelDTO.getDepartureTime(), travelDTO.getArrivalTime());
        travel.setDuration(travelDuration);

        log.info("Travel duration of {} minutes for Travel code {}", travelDuration, travelDTO.getCode());

        Optional<City> destinyCityDAOOptional = cityRepository.findById(travelDTO.getDestinyCityId());

        if (destinyCityDAOOptional.isPresent()) {
            City destinyCity = destinyCityDAOOptional.get();
            travel.setCity(destinyCity);
        }
        travel = travelRepository.save(travel);

        Optional<City> originCityDAOOptional = cityRepository.findById(travelDTO.getOriginCityId());

        if (originCityDAOOptional.isPresent()) {
            City originCity = originCityDAOOptional.get();
            originCity.setTravel(travel);
            cityRepository.save(originCity);
        }

        return travel;
    }

    public Travel getTravel(Long id) {
        Travel travel = null;
        Optional<Travel> travelOptional = travelRepository.findById(id);
        if (travelOptional.isPresent()) {
            travel = travelOptional.get();
        }
        return travel;
    }

    public void deleteTravel(Long id) {
        travelRepository.deleteById(id);
    }

    /**
     * Update of Travel changes the following fields:
     * - DepartureTime
     * - ArrivalTime
     * and calculate the duration time based in the formula:
     * - (arrivalTime - departureTime) = duration
     *
     * @param id
     * @param travelUpdateDTO
     * @return
     */
    public Travel updateTravel(Long id, TravelUpdateDTO travelUpdateDTO) {
        Travel travel = null;
        Optional<Travel> travelOptional = travelRepository.findById(id);
        if (!travelOptional.isPresent()) {
            return travel;
        }
        travel = travelOptional.get();

        Long travelDuration = calculateTravelDuration(travelUpdateDTO.getDepartureTime(), travelUpdateDTO.getArrivalTime());

        log.info("Travel duration of {} minutes for Travel code {}", travelDuration, travel.getCode());

        travel.setDepartureTime(travelUpdateDTO.getDepartureTime().toString());
        travel.setArrivalTime(travelUpdateDTO.getArrivalTime().toString());
        travel.setDuration(travelDuration);

        travel = travelRepository.save(travel);

        return travel;
    }

    /**
     * Calculate the duration of a Travel.
     * The duration is in minutes and the formula of calculation is (arrivalTime - departureTime) = duration
     *
     * @param departureTime
     * @param arrivalTime
     * @return
     */
    private Long calculateTravelDuration(LocalTime departureTime, LocalTime arrivalTime) {
        LocalDate now = LocalDate.now();

        LocalDateTime departure = LocalDateTime.of(now, departureTime);
        LocalDateTime arrival = LocalDateTime.of(now, arrivalTime);

        if (departureTime.isAfter(arrivalTime)) {
            arrival = arrival.plusDays(1);
        }
        Long travelDuration = departure.until(arrival, MINUTES);

        return travelDuration;
    }
}
