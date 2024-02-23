package com.sms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sms.dto.CourseDTO;
import com.sms.dto.CourseInfoDTO;
import com.sms.model.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	@Query("SELECT new com.sms.dto.CourseDTO(c.id,"
			+ "c.name, c.description, c.status) FROM Course c WHERE status = :status")
	Page<CourseDTO> findCoursesByStatus(@Param("status") int status, Pageable pageable);
	
	@Modifying
	@Query("UPDATE Course c SET c.status = 0 WHERE c.id = :id")
	void terminateCourse(@Param("id") Long id);
	
	@Query("SELECT c from Course c JOIN c.batches batch JOIN c.modules module WHERE c.id = :id AND batch.status = 2 "
			+ "AND module.status = 1")
	Optional<Course> findCourseInfo(@Param("id") Long id);
	
	List<Course> findByNameContainingIgnoreCase(String name);
	
	@Query("SELECT c FROM Course c WHERE c.id = :id")
	Course findCourseById(@Param("id") Long id);
}
