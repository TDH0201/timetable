package com.tbk.ThoiKhoaBieu.convert;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tbk.ThoiKhoaBieu.entity.SubjectEntity;
import com.tbk.ThoiKhoaBieu.entity.TeacherEntity;
import com.tbk.ThoiKhoaBieu.model.SubjectDto;
import com.tbk.ThoiKhoaBieu.model.TeacherDto;

@Component
public class TeacherConvert {

	public TeacherDto toDto(TeacherEntity entity) {

		TeacherDto teacherDto = new TeacherDto();
		teacherDto.setId(entity.getId());
		teacherDto.setName(entity.getName());
		teacherDto.setAddress(entity.getAddress());
		teacherDto.setBirthDay(entity.getBirthDay());
		teacherDto.setPhone(entity.getPhone());

		List<SubjectDto> subjectList = entity.getSubjectIds().stream().map(dto -> {

			SubjectDto subjects = new SubjectDto();
			subjects.setId(dto.getId());
			subjects.setName(dto.getName());
			subjects.setNumberTime(dto.getNumberTime());
			subjects.setGrade(dto.getGrade());
			subjects.setTeachIds(dto.getTeachIds());

			return subjects;
		}).collect(Collectors.toList());
		teacherDto.setSubjectIds(subjectList);

		teacherDto.setIsTeacherClass(entity.getIsTeacherClass());

		return teacherDto;
	}

	public TeacherEntity toEntity(TeacherDto teacherDto) {

		TeacherEntity teacherEntity = new TeacherEntity();
		teacherEntity.setName(teacherDto.getName());
		teacherEntity.setAddress(teacherDto.getAddress());
		teacherEntity.setBirthDay(teacherDto.getBirthDay());
		teacherEntity.setPhone(teacherDto.getPhone());
		teacherEntity.setIsTeacherClass(teacherDto.getIsTeacherClass());

		List<SubjectEntity> subjectList = teacherDto.getSubjectIds().stream().map(dto -> {

			SubjectEntity subjects = new SubjectEntity();
			subjects.setId(dto.getId());

			return subjects;
		}).collect(Collectors.toList());
		teacherEntity.setSubjectIds(subjectList);

		return teacherEntity;
	}
}
