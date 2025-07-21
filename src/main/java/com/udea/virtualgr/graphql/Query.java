package com.udea.virtualgr.graphql;

import com.udea.virtualgr.entity.Reservation;
import com.udea.virtualgr.repository.ReservationRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    private final ReservationRepository reservationRepository;

    public Query(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }
}



