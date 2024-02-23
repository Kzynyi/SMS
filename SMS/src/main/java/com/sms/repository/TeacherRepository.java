package com.sms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sms.dto.TeacherDTO;
import com.sms.model.entity.Teacher;
import java.util.List;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	
	Optional<Teacher> findByEmail(String email);
	
	@Modifying
	@Query("UPDATE Teacher t SET t.status = 4 WHERE t.id = :id")
	void retireTeacher(@Param("id") Long id);
	
	@Query("SELECT new com.sms.dto.TeacherDTO(t.id,t.name,t.email) FROM Teacher t")
	List<TeacherDTO> findAllActiveTeacher();
	
}
