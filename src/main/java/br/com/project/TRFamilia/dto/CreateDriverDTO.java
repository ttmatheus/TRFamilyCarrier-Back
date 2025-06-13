package br.com.project.TRFamilia.dto;

import java.time.LocalDateTime;
import java.util.Map;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateDriverDTO {
	private Long userId;

	@CPF(message = "CPF is not valid")
	private String cpf;

	@NotBlank
	private String licenseNumber;

	@NotNull(message = "License expiration date is mandatory")
	private LocalDateTime licenseExpiration;

	@NotBlank
	private String phoneNumber;

	@NotBlank
	private String address;

	@NotNull
	private LocalDateTime birthDate;

	@NotNull
	private LocalDateTime hireDate;

	@NotNull
	private Map<String, String> stats;

	@NotNull
	private Long truckId;

    public Long getUser() {
        return userId;
    }

    public void setUser(Long userId) {
        this.userId = userId;
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

    public Long getTrucks() {
        return truckId;
    }

    public void setTrucks(Long truckId) {
        this.truckId = truckId;
    }
}
