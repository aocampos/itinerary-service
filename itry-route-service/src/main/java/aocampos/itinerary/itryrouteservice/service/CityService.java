package aocampos.itinerary.itryrouteservice.service;

import aocampos.itinerary.itryrouteservice.controller.dto.CityDTO;
import aocampos.itinerary.itryrouteservice.model.City;
import aocampos.itinerary.itryrouteservice.repository.CityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CityService {

    private CityRepository cityRepository;
    private ObjectMapper mapper;

    @Autowired
    public CityService(CityRepository cityRepository,
                       ObjectMapper mapper) {
        this.cityRepository = cityRepository;
        this.mapper = mapper;
    }

    public City createCity(City city) {
        city = cityRepository.save(city);
        return city;
    }

    public List<City> listAllCities() {

        List<City> cities = StreamSupport.stream(cityRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return cities;
    }

    public City getCity(Long id) {
        City city = null;
        Optional<City> cityOptional = cityRepository.findById(id);
        if (cityOptional.isPresent()) {
            city = cityOptional.get();
        }
        return city;
    }

    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }

    public City updateCity(Long id, CityDTO cityDTO) {
        City city = null;
        Optional<City> cityOptional = cityRepository.findById(id);
        if (cityOptional.isPresent()) {
            city = cityOptional.get();
            city.setName(cityDTO.getName());

            cityRepository.save(city);
        }
        return city;
    }
}
