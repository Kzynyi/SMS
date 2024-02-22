package com.sms.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.internal.build.AllowPrintStacktrace;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {

	@Serial
	private static final long serialVersionUID = -115528545447067349L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "batch_id")
	private Batch batch;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	
	private LocalDate enrolledDate;
	
	private int status;
	
}
