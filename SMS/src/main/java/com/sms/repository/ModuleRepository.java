package com.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sms.dto.ModuleDTO;
import com.sms.model.entity.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long>{

	@Modifying
	@Query("UPDATE Module m SET m.status = 0 WHERE m.id = :id")
	void terminateModule(@Param("id") Long moduleId);
	
	@Query("SELECT new com.sms.dto.ModuleDTO(m.id, m.name, m.description) FROM Module m"
			+ " WHERE m.course.id = :id AND m.status = 1")
	List<ModuleDTO> findActiveModuleDTOForCourse(@Param("id") Long courseid);
	
	@Query("SELECT m FROM Module m WHERE m.course.id = : id AND m.status = 1")
	List<Module> findActiveModulesForCourse(@Param("id") Long courseId);
	
}
