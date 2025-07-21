package com.udea.virtualgr.service;

import com.udea.virtualgr.entity.Flight;
import com.udea.virtualgr.entity.Reservation;
import com.udea.virtualgr.repository.FlightRepository;
import com.udea.virtualgr.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final FlightRepository flightRepository;

    public ReservationService(ReservationRepository reservationRepository, FlightRepository flightRepository) {
        this.reservationRepository = reservationRepository;
        this.flightRepository = flightRepository;
    }

    public Reservation reserveFlight(Long flightId, String passengerName, String seatNumber) {
        return flightRepository.findById(flightId)
                .map(flight -> {
                    if (flight.getSeatsAvailable() <= 0) {
                        throw new RuntimeException("🚫 No hay asientos disponibles para el vuelo " + flight.getFlightNumber());
                    }

                    // Actualizar asientos disponibles
                    flight.setSeatsAvailable(flight.getSeatsAvailable() - 1);

                    Reservation reservation = new Reservation();
                    reservation.setPassengerName(passengerName);
                    reservation.setSeatNumber(seatNumber);
                    reservation.setFlight(flight);
                    reservation.setReservationCode(generateReservationCode(flight.getFlightNumber()));

                    try {
                        return reservationRepository.save(reservation);
                    } catch (Exception e) {
                        throw new RuntimeException("❌ Error al guardar la reserva: " + e.getMessage(), e);
                    }
                })
                .orElseThrow(() -> new RuntimeException("🛑 Vuelo no encontrado con ID: " + flightId));
    }

    private String generateReservationCode(String flightNumber) {
        return Optional.ofNullable(flightNumber)
                .map(number -> number + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase())
                .orElseThrow(() -> new IllegalArgumentException("⚠️ El número de vuelo no puede ser null"));
    }

    public boolean deleteById(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new IllegalArgumentException("⚠️ Reserva no encontrada con ID: " + id);
        }

        try {
            reservationRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("❌ No se pudo eliminar la reserva con ID: " + id, e);
        }
    }
}


