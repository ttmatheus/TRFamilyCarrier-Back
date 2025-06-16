package br.com.project.TRFamilia.dto;

import br.com.project.TRFamilia.models.Trip;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TripDTO {
    private Long id;
    private String destination;
    private String status;
    private String driverName;

    // Construtor, getters e setters

    public TripDTO(Trip trip) {
        this.id = trip.getId();
        this.destination = trip.getDestination();
        this.status = trip.getStatus().toString();
        this.driverName = trip.getDriver().getUser().getName(); // exemplo
    }
}