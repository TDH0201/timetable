package com.tbk.ThoiKhoaBieu.convert;

import org.springframework.stereotype.Component;

import com.tbk.ThoiKhoaBieu.entity.ClassEntity;
import com.tbk.ThoiKhoaBieu.model.ClassDto;

@Component
public class ClassConvert {

	public ClassDto toDto(ClassEntity classEntity) {
		ClassDto classDto = new ClassDto();
		classDto.setId(classEntity.getId());
		classDto.setClassname(classEntity.getClassname());
		classDto.setClassSize(classEntity.getClassSize());
		classDto.setGrade(classEntity.getGrade());
		classDto.setTimeLearn(classEntity.getTimeLearn());

		return classDto;
	}

	public ClassEntity toEnity(ClassDto classDto) {

		ClassEntity classEntity = new ClassEntity();
		classEntity.setClassname(classDto.getClassname());
		classEntity.setClassSize(classDto.getClassSize());
		classEntity.setGrade(classDto.getGrade());
		classEntity.setTimeLearn(classDto.getTimeLearn());
		
		return classEntity;
	}
}
