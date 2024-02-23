package com.sms.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.sms.model.entity.id.BatchModuleId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@IdClass(BatchModuleId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchModule implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "batch_id")
	private Batch batch;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "module_id")
	private Module module;

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
