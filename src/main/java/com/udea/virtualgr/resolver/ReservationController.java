package com.udea.virtualgr.resolver;

import com.udea.virtualgr.entity.Reservation;
import com.udea.virtualgr.repository.ReservationRepository;
import com.udea.virtualgr.service.ReservationService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationRepository reservationRepository;
    public ReservationController(ReservationService reservationService,
                                 ReservationRepository reservationRepository) {
        this.reservationService = reservationService;
        this.reservationRepository = reservationRepository;
    }
    // Consulta para obtener todas las reservas
    @QueryMapping
    public List<Reservation> reservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations != null ? reservations : List.of(); // Protección contra null
    }
    @MutationMapping
    public Reservation reserveFlight(@Argument Long flightId, @Argument String passengerName, @Argument String seatNumber){
        return reservationService.reserveFlight(flightId, passengerName, seatNumber);
    }
    @MutationMapping
    public boolean deleteReservation(@Argument Long id) {
        return reservationService.deleteById(id);
    }

}



