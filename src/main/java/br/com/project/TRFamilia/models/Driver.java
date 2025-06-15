package br.com.project.TRFamilia.models;

import java.time.LocalDateTime;
import java.util.Map;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.project.TRFamilia.enums.DriverStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "drivers")
@Getter
@Setter
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	private User user;

	private String cpf;

	@Column(name = "license_number")
	private String licenseNumber;

	@Column(name = "license_expiration")
	private LocalDateTime licenseExpiration;

	@Column(name = "phone_number")
	private String phoneNumber;

	private String address;

	@Column(name = "birth_date")
	private LocalDateTime birthDate;

	@Column(name = "hire_date")
	private LocalDateTime hireDate;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(columnDefinition = "jsonb")
	private Map<String, String> stats;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "current_trip_id")
	private Trip trip;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
	@JoinColumn(name = "truck_id")
	private Truck truck;

    @Enumerated(EnumType.STRING)
	private DriverStatus status;

	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

    public Driver() {

    }

    public Driver(User user, String cpf, String licenseNumber, LocalDateTime licenseExpiration,
     String phoneNumber, String address, LocalDateTime birthDate, LocalDateTime hireDate, Map<String,
      String> stats, Truck truck, DriverStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.user = user;
        this.cpf = cpf;
        this.licenseNumber = licenseNumber;
        this.licenseExpiration = licenseExpiration;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.stats = stats;
        this.truck = truck;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
