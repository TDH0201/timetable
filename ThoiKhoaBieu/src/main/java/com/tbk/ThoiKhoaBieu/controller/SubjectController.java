package com.tbk.ThoiKhoaBieu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tbk.ThoiKhoaBieu.model.SubjectDto;
import com.tbk.ThoiKhoaBieu.service.ISubjectService;

@RestController
@RequestMapping("/subject")
public class SubjectController {

	@Autowired
	ISubjectService subjectService;

	@GetMapping("")
	public List<SubjectDto> findAll() {
		return subjectService.findAll();
	}

	@PostMapping("/add")
	public SubjectDto save(@RequestBody SubjectDto subjectDto) {

		return subjectService.save(subjectDto);
	}

}
