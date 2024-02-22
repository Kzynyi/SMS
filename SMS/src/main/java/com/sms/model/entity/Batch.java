package com.sms.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Batch implements Serializable{

	@Serial
	private static final long serialVersionUID = 9219748620907890412L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String name;
	
	private LocalDate startDate;
	
	private int studentLimit;
	
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;
	
	//1 for active, 4 for ended, 2 for future
	private int status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course course;
	
	@OneToMany(mappedBy = "batch", fetch = FetchType.LAZY)
	private List<Student> students;
	
	@OneToMany(mappedBy = "batch", fetch = FetchType.LAZY)
	private List<BatchModule> modules;
	
	private LocalDateTime createdOn;
	
	private LocalDateTime updatedOn;
	
	@PrePersist
	void prePersist() {
		this.createdOn = LocalDateTime.now();
		this.updatedOn = LocalDateTime.now();
	}
	
	@PreUpdate
	void preUpdate() {
		this.updatedOn = LocalDateTime.now();
	}
}
