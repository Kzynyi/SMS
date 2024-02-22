package com.sms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sms.dto.BatchCreateDTO;
import com.sms.dto.BatchDTO;
import com.sms.dto.BatchStatusDTO;

@Service
public interface BatchService {

	Long createBatch(BatchCreateDTO batch);
	
	int endBatch(Long batchId);
	
	List<BatchDTO> getAllBatchesForCourse(Long courseId);
	
	BatchDTO getActiveBatchForCourse(Long courseId);
	
	BatchStatusDTO getBatchStatus(Long batchId);
	
}
