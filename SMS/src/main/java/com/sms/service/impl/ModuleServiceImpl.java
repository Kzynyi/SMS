package com.sms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sms.dto.ModuleCreateDTO;
import com.sms.dto.ModuleDTO;
import com.sms.dto.ModuleModifyDTO;
import com.sms.model.entity.Module;
import com.sms.model.mapper.ModuleMapper;
import com.sms.repository.ModuleRepository;
import com.sms.service.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {

	
	private final ModuleMapper moduleMapper;
	private final ModuleRepository moduleRepository;
	
	
	public ModuleServiceImpl(ModuleMapper moduleMapper, ModuleRepository moduleRepository) {
		this.moduleMapper = moduleMapper;
		this.moduleRepository = moduleRepository;
	}

	@Override
	public Long createModule(ModuleCreateDTO dto) {
		Module module = moduleMapper.toModule(dto);
		try {
			module = moduleRepository.save(module);
			return module.getId();
		}catch(Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Long modifyModule(ModuleModifyDTO dto) {
		Module module = moduleMapper.toModule(dto);
		try {
			module = moduleRepository.save(module);
			return module.getId();
		}catch(Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

	@Override
	public Long terminateModule(Long moduleId) {
		try {
			moduleRepository.terminateModule(moduleId);
			return moduleId;
		}catch(Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

	@Override
	public List<ModuleDTO> getActiveModuleDTOForCourse(Long courseId) {
		return moduleRepository.findActiveModuleDTOForCourse(courseId);
	}

	@Override
	public ModuleDTO getModule(Long moduleId) {
		Optional<Module> result = moduleRepository.findById(moduleId);
		if(result.isPresent()) {
			return moduleMapper.toModuleDTO(result.get());
		}
		return null;
	}

	@Override
	public List<Module> getActiveModulesForCourse(Long courseId) {
		return moduleRepository.findActiveModulesForCourse(courseId);
	}

	
}
