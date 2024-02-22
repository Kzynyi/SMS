package com.sms.model.entity;

import java.io.Serializable;

import com.sms.model.entity.id.ApplicationId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@IdClass(ApplicationId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application implements Serializable {

	private static final long serialVersionUID = 7705248404562009910L;

	@Id
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "batch_id")
	private Batch batch;
	
	private int status;
	
	
}
