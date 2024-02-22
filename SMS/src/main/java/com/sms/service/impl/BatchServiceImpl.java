package com.sms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sms.dto.BatchCreateDTO;
import com.sms.dto.BatchDTO;
import com.sms.dto.BatchStatusDTO;
import com.sms.model.entity.Batch;
import com.sms.model.mapper.BatchMapper;
import com.sms.repository.BatchRepository;
import com.sms.service.BatchModuleService;
import com.sms.service.BatchService;
import com.sms.service.ModuleService;
import com.sms.model.entity.Module;

@Service
public class BatchServiceImpl implements BatchService {

	private final BatchMapper batchMapper;
	private final BatchRepository batchRepository;
	private final ModuleService moduleService;
	private final BatchModuleService batchModuleService;
	
	public BatchServiceImpl(BatchMapper batchMapper, 
							BatchRepository batchRepository,
							ModuleService moduleService,
							BatchModuleService batchModuleService) {
		this.batchMapper = batchMapper;
		this.batchRepository = batchRepository;
		this.moduleService = moduleService;
		this.batchModuleService = batchModuleService;
		
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Long createBatch(BatchCreateDTO batch) {
		Batch b = batchMapper.toBatch(batch);
		try {
			b = batchRepository.save(b);
			List<Module> modules = moduleService.getActiveModulesForCourse(b.getCourse().getId());
			boolean success = batchModuleService.createModulesForBatch(b, modules);
			if(!success) {
				return 0L;
			}
			return b.getId();
		}catch(Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

	@Override
	public int endBatch(Long batchId) {
		try {
			batchRepository.endBatch(batchId);
			return 1;
		}catch(Exception e) {
			return 0;
		}
	}

	@Override
	public List<BatchDTO> getAllBatchesForCourse(Long courseId) {
		return batchRepository.findAllBatchesForCourse(courseId);
	}

	@Override
	public BatchDTO getActiveBatchForCourse(Long courseId) {
		Optional<BatchDTO> result = batchRepository.findActiveBatchForCourse(courseId);
		if(result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public BatchStatusDTO getBatchStatus(Long batchId) {
		Optional<Batch> result = batchRepository.findById(batchId);
		if(result.isPresent()) {
		 return batchMapper.toBatchStatusDTO(result.get());	
		}
		return null;
	}

}
