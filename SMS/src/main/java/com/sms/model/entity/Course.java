package com.sms.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course implements Serializable {

	@Serial
	private static final long serialVersionUID = -6560878677445966141L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String name;
	
	private String description;
	
	private int status;

	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	private List<Batch> batches;
	
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	private List<Module> modules;
	
	
}
