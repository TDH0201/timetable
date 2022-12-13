package com.tbk.ThoiKhoaBieu.service;

import java.util.List;

import com.tbk.ThoiKhoaBieu.model.ClassDto;

public interface IClassService {
	
	List<ClassDto> findAll();
	
	ClassDto save(ClassDto classDto);
}
