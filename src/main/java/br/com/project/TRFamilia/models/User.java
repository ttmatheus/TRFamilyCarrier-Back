package br.com.project.TRFamilia.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.project.TRFamilia.enums.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(unique = true)
	private String email;

	private String password;

	@Column(name = "user_type")
	@Enumerated(EnumType.STRING)
	private UserType userType;

	@Column(name = "is_active")
	private boolean active;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public User() {}

	public User(String name, String email, String password, UserType userType, boolean active) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.userType = userType;
		this.active = active;
	}

	public String getHashPassword() { return password; }

	public void setHashPassword(String password) { this.password = password; }

	public String getEmail() { return email; }

	public void setEmail(String email) { this.email = email; }

	public Long getId() { return id; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public UserType getType() { return userType; }

	public void setType(UserType type) { this.userType = type; }

	public boolean isActive() { return active; }

	public void setActive(boolean active) { this.active = active; }

	public LocalDateTime getCreatedAt() { return createdAt; }

	public LocalDateTime getUpdatedAt() { return updatedAt; }
}
