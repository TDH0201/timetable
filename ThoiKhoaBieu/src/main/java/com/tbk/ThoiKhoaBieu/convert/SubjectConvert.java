package com.tbk.ThoiKhoaBieu.convert;

import org.springframework.stereotype.Component;

import com.tbk.ThoiKhoaBieu.entity.SubjectEntity;
import com.tbk.ThoiKhoaBieu.model.SubjectDto;

@Component
public class SubjectConvert {

	public SubjectDto toDto(SubjectEntity entity) {

		SubjectDto subjectDto = new SubjectDto();
		subjectDto.setId(entity.getId());
		subjectDto.setName(entity.getName());
		subjectDto.setGrade(entity.getGrade());
		subjectDto.setNumberTime(entity.getNumberTime());
		subjectDto.setTeachIds(entity.getTeachIds());
		
		return subjectDto;
	}

	public SubjectEntity toEntity(SubjectDto subjectDto) {

		SubjectEntity subjectEntity = new SubjectEntity();
		subjectEntity.setName(subjectDto.getName());
		subjectEntity.setGrade(subjectDto.getGrade());
		subjectEntity.setNumberTime(subjectDto.getNumberTime());
		subjectEntity.setTeachIds(subjectDto.getTeachIds());

		return subjectEntity;
	}
}
