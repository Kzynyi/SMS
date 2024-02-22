package com.sms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sms.dto.BatchModuleDTO;
import com.sms.model.entity.Batch;
import com.sms.model.entity.BatchModule;
import com.sms.model.entity.Module;
import com.sms.model.mapper.BatchModuleMapper;
import com.sms.repository.BatchModuleRepository;
import com.sms.service.BatchModuleService;

@Service
public class BatchModuleServiceImpl implements BatchModuleService {

	private final BatchModuleRepository batchModuleRepository;
	private final BatchModuleMapper batchModuleMapper;
	
	public BatchModuleServiceImpl(BatchModuleRepository batchModuleRepository, BatchModuleMapper batchModuleMapper) {
		this.batchModuleRepository = batchModuleRepository;
		this.batchModuleMapper = batchModuleMapper;
	}

	@Override
	public boolean createModulesForBatch(Batch batch, List<Module> modules) {
		
		try {
			for(Module m : modules) {
				BatchModule batchModule = new BatchModule();
				batchModule.setBatch(batch);
				batchModule.setModule(m);
				batchModuleRepository.save(batchModule);
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	
	@Override
	public List<BatchModule> getModulesForBatch(Long batchId) {
		return batchModuleRepository.findModulesForBatch(batchId);
	}

	@Override
	public List<BatchModuleDTO> getModuleDTOForBatch(Long batchId) {
		return batchModuleRepository.findModuleDTOForBatch(batchId);
	}


}
