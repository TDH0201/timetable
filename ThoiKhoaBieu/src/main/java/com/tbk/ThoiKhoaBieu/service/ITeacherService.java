package com.tbk.ThoiKhoaBieu.service;

import java.util.List;
import java.util.Optional;

import com.tbk.ThoiKhoaBieu.entity.TeacherEntity;
import com.tbk.ThoiKhoaBieu.model.TeacherDto;

public interface ITeacherService {
	List<TeacherDto> findAll();

	TeacherDto save(TeacherDto teacher);

	Optional<TeacherEntity> findById(Long id);
	
}
