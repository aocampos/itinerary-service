package aocampos.itinerary.itryrouteservice.service;

import aocampos.itinerary.itryrouteservice.controller.dto.TravelDTO;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.MINUTES;

public class TravelServiceTest {

    @Test
    public void createTravel() {

        TravelDTO travelDTO = new TravelDTO();
        travelDTO.setDepartureTime(LocalTime.parse("23:50"));
        travelDTO.setArrivalTime(LocalTime.parse("00:10"));

        System.out.println(travelDTO.getDepartureTime().until(travelDTO.getArrivalTime(), MINUTES));

        LocalTime departureTime = LocalTime.parse("23:50");
        LocalTime arrivalTime = LocalTime.parse("01:10");

        LocalDate now = LocalDate.now();

        LocalDateTime d1 = LocalDateTime.of(now, departureTime);
        LocalDateTime d2 = LocalDateTime.of(now, arrivalTime);

        if (departureTime.isAfter(arrivalTime)) {
            d2 = d2.plusDays(1);
        }

        System.out.println(d1.until(d2, MINUTES));

    }
}