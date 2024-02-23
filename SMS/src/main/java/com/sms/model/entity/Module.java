package com.sms.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Module implements Serializable {

	@Serial
	private static final long serialVersionUID = -4999862314380417419L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String name;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	private int status;
	
	private LocalDateTime createdOn;
	
	private LocalDateTime updatedOn;
	

	@PrePersist
	void prePersist() {
		this.status = 1;
		this.createdOn = LocalDateTime.now();
		this.updatedOn = LocalDateTime.now();
	}
	
	@PreUpdate
	void preUpdate() {
		this.updatedOn = LocalDateTime.now();
	}
	
}
