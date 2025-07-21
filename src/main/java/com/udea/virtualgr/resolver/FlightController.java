package com.udea.virtualgr.resolver;

import com.udea.virtualgr.entity.Flight;
import com.udea.virtualgr.service.FlightService;
import org.springframework.data.repository.query.Param;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @QueryMapping
    public List<Flight> allFlights(){
        return flightService.getAllFlights();
    }

    @QueryMapping
    public Flight flightById(@Argument Long id){
        return flightService.getFlightById(id);
    }


    @MutationMapping
    public Flight addFlight(@Argument String flightNumber,@Argument int seatsAvailable,
                            @Argument String origin, @Argument String destination,
                            @Argument String departureTime, @Argument String arrivalTime){
        //convertir las cadenas de texto a LocalDateTime
        LocalDateTime departure = LocalDateTime.parse(departureTime);
        LocalDateTime arrival = LocalDateTime.parse(arrivalTime);

        return flightService.addFlight(flightNumber,seatsAvailable,origin,destination,departure,arrival);
    }
}
