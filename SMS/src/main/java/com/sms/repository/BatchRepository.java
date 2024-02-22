package com.sms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sms.dto.BatchDTO;
import com.sms.model.entity.Batch;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long>{

	@Modifying
	@Query("UPDATE Batch b SET b.status = 4 WHERE b.id = :id")
	void endBatch(@Param("id") Long batchId);
	
	@Query("SELECT new com.sms.dto.BatchDTO(b.id,b.name,b.startDate,b.studentLimit, b.status) "
			+ "FROM Batch b where b.course.id = :id")
	List<BatchDTO> findAllBatchesForCourse(@Param("id") Long courseId);
	
	@Query("SELECT new com.sms.dto.BatchDTO(b.id,b.name,b.startDate,b.studentLimit, b.status) "
			+ "FROM Batch b where b.course.id = :id AND b.status = 1")
	Optional<BatchDTO> findActiveBatchForCourse(@Param("id") Long courseId);
	
}
