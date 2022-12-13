package com.tbk.ThoiKhoaBieu.service;

import java.util.List;

import com.tbk.ThoiKhoaBieu.model.SubjectDto;

public interface ISubjectService {

	List<SubjectDto> findAll();
	
	SubjectDto save(SubjectDto subjectDto);
	
}
