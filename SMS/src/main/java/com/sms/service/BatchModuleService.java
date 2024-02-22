package com.sms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sms.dto.BatchModuleDTO;
import com.sms.model.entity.Batch;
import com.sms.model.entity.BatchModule;
import com.sms.model.entity.Module;

@Service
public interface BatchModuleService {

	boolean createModulesForBatch(Batch batch, List<Module> modules);
	
	List<BatchModule> getModulesForBatch(Long batchId);
	
	List<BatchModuleDTO> getModuleDTOForBatch(Long batchId);
	
}
