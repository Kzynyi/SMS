package com.sms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sms.model.entity.Module;
import com.sms.dto.ModuleCreateDTO;
import com.sms.dto.ModuleDTO;
import com.sms.dto.ModuleModifyDTO;

@Service
public interface ModuleService {

	Long createModule(ModuleCreateDTO dto);
	
	Long modifyModule(ModuleModifyDTO dto);
	
	Long terminateModule(Long moduleId);
	
	List<ModuleDTO> getActiveModuleDTOForCourse(Long courseId);
	
	List<Module> getActiveModulesForCourse(Long courseId);
	
	ModuleDTO getModule(Long moduleId);
	
}
