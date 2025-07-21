package com.udea.virtualgr.graphql.mutation;

import com.udea.virtualgr.entity.Reservation;
import com.udea.virtualgr.service.ReservationService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class ReservationMutation implements GraphQLMutationResolver {

    private final ReservationService reservationService;

    public ReservationMutation(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public Reservation reserveFlight(Long flightId, String passengerName, String seatNumber) {
        return reservationService.reserveFlight(flightId, passengerName, seatNumber);
    }

    public boolean deleteReservation(Long id) {
        return reservationService.deleteById(id);
    }
}


