// src/main/java/br/com/project/TRFamilia/dto/DriverResponseDTO.java
package br.com.project.TRFamilia.dto;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private ResponseTruckDTO truck;
    private String licenseNumber;
    private LocalDateTime licenseExpiration;
    private Map<String, String> stats;

    public DriverResponseDTO(Long id, UserResponseDTO user, ResponseTruckDTO truck, String licenseNumber, LocalDateTime licenseExpiration, Map<String, String> stats) {
        this.id = id;
        this.user = user;
        this.truck = truck;
        this.licenseNumber = licenseNumber;
        this.licenseExpiration = licenseExpiration;
        this.stats = stats;
    }

    public DriverResponseDTO() {
    }
}