package com.sms.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sms.dto.ModuleCreateDTO;
import com.sms.dto.ModuleDTO;
import com.sms.dto.ModuleModifyDTO;
import com.sms.model.entity.Module;
import com.sms.model.mapper.util.ModuleMapperUtil;

@Mapper(componentModel = "spring", uses = {ModuleMapperUtil.class})
public interface ModuleMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(source ="courseId", target ="course")
	Module toModule(ModuleCreateDTO dto);
	
	@Mapping(target = "course.batches", ignore = true)
	Module toModule(ModuleModifyDTO dto);
	
	ModuleDTO toModuleDTO(Module module);
}
