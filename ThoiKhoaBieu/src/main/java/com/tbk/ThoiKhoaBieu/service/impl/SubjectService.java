package com.tbk.ThoiKhoaBieu.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tbk.ThoiKhoaBieu.convert.SubjectConvert;
import com.tbk.ThoiKhoaBieu.model.SubjectDto;
import com.tbk.ThoiKhoaBieu.repository.SubjectRepository;
import com.tbk.ThoiKhoaBieu.service.ISubjectService;

@Service
public class SubjectService implements ISubjectService {

	@Autowired
	SubjectRepository subjectRepository;

	@Autowired
	SubjectConvert subjectCoonvert;

	@Override
	public List<SubjectDto> findAll() {

		return subjectRepository.findAll().stream().map(dto -> subjectCoonvert.toDto(dto)).collect(Collectors.toList());
	}

	@Override
	public SubjectDto save(SubjectDto subjectDto) {
		return subjectCoonvert.toDto(subjectRepository.save(subjectCoonvert.toEntity(subjectDto)));
	}

}
