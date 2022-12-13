package com.tbk.ThoiKhoaBieu.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tbk.ThoiKhoaBieu.convert.ClassConvert;
import com.tbk.ThoiKhoaBieu.model.ClassDto;
import com.tbk.ThoiKhoaBieu.repository.ClassRepository;
import com.tbk.ThoiKhoaBieu.service.IClassService;

@Service
public class ClassService implements IClassService {

	@Autowired
	ClassRepository classRepository;

	@Autowired
	ClassConvert classConvert;

	@Override
	public List<ClassDto> findAll() {
		return classRepository.findAll().stream().map(dto -> classConvert.toDto(dto)).collect(Collectors.toList());

	}

	@Override
	public ClassDto save(ClassDto classDto) {
		return classConvert.toDto(classRepository.save(classConvert.toEnity(classDto)));
	}

}
