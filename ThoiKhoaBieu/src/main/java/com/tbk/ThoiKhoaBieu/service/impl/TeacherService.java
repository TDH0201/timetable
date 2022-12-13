package com.tbk.ThoiKhoaBieu.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tbk.ThoiKhoaBieu.convert.TeacherConvert;
import com.tbk.ThoiKhoaBieu.entity.TeacherEntity;
import com.tbk.ThoiKhoaBieu.model.TeacherDto;
import com.tbk.ThoiKhoaBieu.repository.TeacherRepository;
import com.tbk.ThoiKhoaBieu.service.ITeacherService;

@Service
public class TeacherService implements ITeacherService {

	@Autowired
	TeacherRepository teacherRepository;

	@Autowired
	TeacherConvert teacherConvert;

	@Override
	public List<TeacherDto> findAll() {
		return teacherRepository.findAll().stream().map(dto -> {
			return teacherConvert.toDto(dto);
		}).collect(Collectors.toList());

	}

	@Override
	public TeacherDto save(TeacherDto teacher) {
		return teacherConvert.toDto(teacherRepository.save(teacherConvert.toEntity(teacher)));
	}

	@Override
	public Optional<TeacherEntity> findById(Long id) {

		return teacherRepository.findById(id);
	}

}
