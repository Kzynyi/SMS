package com.sms.model.entity.id;

import java.io.Serial;
import java.io.Serializable;

import com.sms.model.entity.Batch;
import com.sms.model.entity.Module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchModuleId implements Serializable {

	@Serial
	private static final long serialVersionUID = -2262677016460980038L;

	private Batch batch;
	
	private Module module;
	
}
