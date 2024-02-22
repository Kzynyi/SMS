package com.sms.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher implements UserDetails {

	@Serial
	private static final long serialVersionUID = -3187136928777921683L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private Role role;
	
	private int status;
	
	@OneToMany(mappedBy = "teacher")
	private List<Batch> batches;

	private LocalDateTime createdOn;
	
	private LocalDateTime updatedOn;
	
	@PrePersist
	public void prePersist() {
		this.createdOn = LocalDateTime.now();
		this.updatedOn = LocalDateTime.now();
		this.role = Role.ROLE_TEACHER;
	}
	
	@PreUpdate
	public void preUpdate() {
		this.updatedOn = LocalDateTime.now();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		if(this.status == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		if(this.status == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		if(this.status == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isEnabled() {
		if(this.status == 1) {
			return true;
		}
		return false;
	}
	
	
}
