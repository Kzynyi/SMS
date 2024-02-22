package com.sms.model.entity.id;

import java.io.Serializable;

import com.sms.model.entity.Account;
import com.sms.model.entity.Batch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationId implements Serializable {

	private static final long serialVersionUID = 2861460357734761803L;

	private Account account;
	
	private Batch batch; 
}
