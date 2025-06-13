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

@Entity
@Table(name = "drivers")
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

	public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public LocalDateTime getLicenseExpiration() {
        return licenseExpiration;
    }

    public void setLicenseExpiration(LocalDateTime licenseExpiration) {
        this.licenseExpiration = licenseExpiration;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDateTime hireDate) {
        this.hireDate = hireDate;
    }

    public Map<String, String> getStats() {
        return stats;
    }

    public void setStats(Map<String, String> stats) {
        this.stats = stats;
    }

    public Trip getCurrentTripId() {
        return trip;
    }

    public void setCurrentTripId(Trip trip) {
        this.trip = trip;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
