package com.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sms.dto.BatchModuleDTO;
import com.sms.model.entity.Batch;
import com.sms.model.entity.BatchModule;
import com.sms.model.entity.id.BatchModuleId;

public interface BatchModuleRepository extends JpaRepository<BatchModule, BatchModuleId>{

	@Query("SELECT bm FROM BatchModule bm WHERE bm.batch.id = :id")
	List<BatchModule> findModulesForBatch(@Param("id") Long batchId);
	
	@Query(
	"SELECT new com.sms.dto.BatchModuleDTO(bm.module.name, bm.module.description) "
	+ "FROM BatchModule bm WHERE bm.batch.id = :id")
	List<BatchModuleDTO> findModuleDTOForBatch(@Param("id") Long batchId);
}
