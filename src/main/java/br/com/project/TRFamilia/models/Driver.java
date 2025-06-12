package br.com.project.TRFamilia.models;

import java.time.LocalDateTime;
import java.util.Map;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import br.com.project.TRFamilia.converters.JsonbConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
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

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	private User user;

	@CPF
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

	@Convert(converter = JsonbConverter.class)
	@Column(columnDefinition = "jsonb")
	private Map<String, String> stats;

	@Column(name = "current_trip_id")
	private Trip trip;

	@OneToOne
	@JoinColumn(name = "truck_id")
	private Truck truck;

	private String status;

	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

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

    public Truck getTrucks() {
        return truck;
    }

    public void setTrucks(Truck truck) {
        this.truck = truck;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
